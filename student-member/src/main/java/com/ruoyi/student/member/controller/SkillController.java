package com.ruoyi.student.member.controller;


import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.student.member.domain.Skill;
import com.ruoyi.student.member.domain.vo.SkillVo;
import com.ruoyi.student.member.service.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private ISkillService skillService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/publish")
    public AjaxResult publish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("availableTime") String availableTime,
            @RequestParam("method") String method,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "customCategory", required = false) String customCategory,
            @RequestParam(value = "coverImg", required = false) MultipartFile coverImg,
            HttpServletRequest request) {

        if (coverImg == null || coverImg.isEmpty()) {
            return AjaxResult.error("请上传封面图片");
        }

        LoginUser loginUser = tokenService.getLoginUser(request);
        Long currentUserId = loginUser.getUser().getUserId();

        try {
            String baseDir = RuoYiConfig.getProfile() + File.separator + "skill";
            String coverImgPath = FileUploadUtils.upload(baseDir, coverImg);

            Skill skill = new Skill();
            skill.setTitle(title);
            skill.setDescription(description);
            skill.setAvailableTime(availableTime);
            skill.setCoverImg(coverImgPath);
            skill.setCategoryId(categoryId);
            skill.setCustomCategory(customCategory);

            if (StringUtils.isNotBlank(method)) {
                skill.setMethod(Byte.valueOf(method.trim()));
            }

            skillService.publishSkill(skill, currentUserId);
            return AjaxResult.success("提交成功，请等待审核");

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("封面图上传失败：" + e.getMessage());
        }
    }
    @GetMapping("/me")
    public AjaxResult getMyPublishedSkills(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        Long userId = loginUser.getUser().getUserId();

        List<Skill> skills = skillService.getMyPublishedSkills(userId);
        return AjaxResult.success(skills);
    }
    @PostMapping("/update")
    public AjaxResult update(
            @RequestParam("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("availableTime") String availableTime,
            @RequestParam("method") String method,
            @RequestParam(value = "coverImg", required = false) MultipartFile coverImg,
            HttpServletRequest request) throws IOException {

        LoginUser loginUser = tokenService.getLoginUser(request);
        Long currentUserId = loginUser.getUser().getUserId();

        Skill oldSkill = skillService.selectSkillById(id);
        if (oldSkill == null || !oldSkill.getUserId().equals(currentUserId)) {
            return AjaxResult.error("技能不存在或无权限修改");
        }

        Skill skill = new Skill();
        skill.setId(id);
        skill.setUserId(currentUserId);
        skill.setTitle(title);
        skill.setDescription(description);
        skill.setAvailableTime(availableTime);

        if (StringUtils.isNotBlank(method)) {
            skill.setMethod(Byte.valueOf(method.trim()));
        }

        String coverImgPath = oldSkill.getCoverImg(); // 保留原图
        if (coverImg != null && !coverImg.isEmpty()) {
            String baseDir = RuoYiConfig.getProfile() + File.separator + "skill";
            coverImgPath = FileUploadUtils.upload(baseDir, coverImg);
        }
        skill.setCoverImg(coverImgPath);

        skillService.updateSkill(skill, coverImg, currentUserId);

        return AjaxResult.success("修改成功，请等待审核");
    }
    @GetMapping("/list")
    public AjaxResult listPublicSkills(@RequestParam(value = "keyword", required = false) String keyword) {
        System.out.println(">>> 接收到 keyword: [" + keyword + "]"); // 看是否为 null 或空
        List<SkillVo> list = skillService.listPublicSkillsForView(keyword);
        return AjaxResult.success(list);
    }
    @GetMapping("/{id}")
    public AjaxResult getSkillDetail(@PathVariable Long id) {
        SkillVo vo = skillService.getSkillDetail(id);
        if (vo == null) {
            return AjaxResult.error("技能不存在或未审核");
        }
        return AjaxResult.success(vo);
    }
    @DeleteMapping("/{id}")
    public AjaxResult deleteSkill(@PathVariable Long id, HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        Long currentUserId = loginUser.getUser().getUserId();

        // 调用 service 删除（需确保只能删除自己的技能）
        skillService.deleteSkillById(id, currentUserId);
        return AjaxResult.success("删除成功");
    }
}
