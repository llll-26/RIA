package com.ruoyi.student.member.controller;

import com.ruoyi.common.annotation.IgnoreAuth;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.student.member.domain.SkillOrder;
import com.ruoyi.student.member.domain.vo.PointsOverviewVO;
import com.ruoyi.student.member.mapper.SkillOrderMapper;
import com.ruoyi.student.member.service.IPointAccountService;
import com.ruoyi.student.member.service.IPointsService;
import com.ruoyi.student.member.service.impl.PdfService;
import com.ruoyi.student.member.util.ProofTokenUtil;
import com.ruoyi.student.member.util.QrCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

@RestController
@RequestMapping("/points")
public class PointsController {

    @Autowired
    private IPointsService pointsService;
    @Autowired
    private SkillOrderMapper skillOrderMapper;
    @Autowired
    private PdfService pdfService;
    @Value("${proof.secret-key}")
    @Autowired
    private String proofSecretKey; // 注入密钥
    @Qualifier("pointAccountServiceImpl")
    @Autowired
    private IPointAccountService pointAccountService;
    @GetMapping
    public AjaxResult overview() {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        PointsOverviewVO vo = pointsService.getPointsOverview(userId);
        return AjaxResult.success(vo);
    }

    @GetMapping("/growth")
    public AjaxResult growth() {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        List<SkillOrder> orders = pointsService.getMyProvidedCompletedOrders(userId);
        return AjaxResult.success(orders);
    }

    @PostMapping("/proof")
    public ResponseEntity<?> generateProof() {
        try {
            Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
            List<SkillOrder> completedOrders = skillOrderMapper.selectMyProvidedCompletedOrders(userId);

            if (completedOrders.isEmpty()) {
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Map.of("code", 400, "message", "您还没有任何已完成的服务记录，无法生成证明"));
            }

            SysUser currentUser = SecurityUtils.getLoginUser().getUser();
            String studentId = currentUser.getStudentId() != null ? currentUser.getStudentId() : currentUser.getUserName();
            String collegeAndMajor = (currentUser.getDept().getDeptName() + " " + currentUser.getMajor()).trim();
            if (collegeAndMajor.isEmpty()) collegeAndMajor = "未填写";

            int totalHours = completedOrders.stream()
                    .mapToInt(o -> o.getDurationHours() != null ? o.getDurationHours() : 0)
                    .sum();

            List<String> skills = completedOrders.stream()
                    .map(SkillOrder::getSkillTitle)
                    .filter(title -> title != null && !title.trim().isEmpty())
                    .distinct()
                    .collect(Collectors.toList());

            // 防伪令牌+二维码
            String token = ProofTokenUtil.generateToken(userId, totalHours, proofSecretKey);

            String verifyUrl = "http://192.168.105.102:8080/points/verify?token=" + token;
            BufferedImage qrImage = QrCodeUtil.generateQrCode(verifyUrl, 150, 150);

            //调用带二维码的新方法
            byte[] pdfBytes = pdfService.generateProofWithQrCode(studentId, collegeAndMajor, totalHours, skills, qrImage);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "service_proof.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            log.error("生成服务证明失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("code", 500, "message", "服务器内部错误，请联系管理员"));
        }
    }
    /**
     * 防伪证书公开验证接口
     */
    @IgnoreAuth
    @GetMapping("/verify")
    public AjaxResult verify(@RequestParam String token) {
        try {
            //调用你已有的验证方法
            ProofTokenUtil.ProofData data = ProofTokenUtil.verifyToken(token, proofSecretKey);

            if (data == null) {
                return AjaxResult.error("无效或已被篡改的验证链接");
            }

            // 可选：检查是否过期（比如超过7天）
            // 这里简单返回成功
            return AjaxResult.success(Map.of(
                    "valid", true,
                    "message", "技能服务证书有效",
                    "userId", data.userId,
                    "totalHours", data.totalHours,
                    "generatedAt", data.generatedAt
            ));

        } catch (Exception e) {
            return AjaxResult.error("验证过程中发生错误");
        }
    }
/*    @PostMapping("/sync")
    public AjaxResult syncUserTotalPoints() {
        Long userId = SecurityUtils.getLoginUser().getUser().getUserId();
        pointAccountService.calibrateTotalPoints(userId);
        log.info("【积分同步】开始同步用户积分...");
        log.info("【积分同步】用户ID: {}", userId);

        pointAccountService.calibrateTotalPoints(userId);

        log.info("【积分同步】同步完成");
        return AjaxResult.success("积分已同步");
    }*/
}
