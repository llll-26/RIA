package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysStudentWhitelist;
import com.ruoyi.system.domain.excel.StudentWhitelistImportDTO;
import com.ruoyi.system.service.ISysStudentWhitelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/system/whitelist")
public class SysStudentWhitelistController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(SysStudentWhitelistController.class);

    @Autowired
    private ISysStudentWhitelistService whitelistService;

    @PostMapping("/importData")
    public AjaxResult importData(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return AjaxResult.error("请上传文件");
        }

        try (InputStream inputStream = file.getInputStream()) {
            List<StudentWhitelistImportDTO> dtoList = new ExcelUtil<>(StudentWhitelistImportDTO.class).importExcel(inputStream);

            List<SysStudentWhitelist> entityList = new ArrayList<>();
            List<String> errorMsgs = new ArrayList<>();

            for (int i = 0; i < dtoList.size(); i++) {
                StudentWhitelistImportDTO dto = dtoList.get(i);
                int rowIndex = i + 1;

                try {
                    if (dto == null || !StringUtils.hasText(dto.getStudentId())) {
                        continue;
                    }
                    if (dto.getDeptId() == null || !StringUtils.hasText(dto.getMajor())) {
                        errorMsgs.add("第" + rowIndex + "行：学院ID或专业不能为空");
                        continue;
                    }

                    SysStudentWhitelist entity = new SysStudentWhitelist();
                    entity.setStudentId(dto.getStudentId());
                    entity.setDeptId(dto.getDeptId());
                    entity.setMajor(dto.getMajor());
                    entity.setImportBatch("batch_" + DateUtils.dateTimeNow());

                    entityList.add(entity);

                } catch (Exception e) {
                    errorMsgs.add("第" + rowIndex + "行：处理异常 - " + e.getMessage());
                }
            }

            if (!entityList.isEmpty()) {
                whitelistService.batchInsertOrUpdate(entityList); // 确保方法名正确
            }

            String msg = String.format("导入完成！共处理 %d 条", entityList.size());
            if (!errorMsgs.isEmpty()) {
                return AjaxResult.warn(msg + "\n警告：\n" + String.join("\n", errorMsgs));
            } else {
                return AjaxResult.success(msg);
            }

        } catch (Exception e) {
            return AjaxResult.error("导入失败：" + e.getMessage());
        }
    }
    @GetMapping("/list")
    public AjaxResult list() {
        List<SysStudentWhitelist> list = whitelistService.selectList(null);
        return AjaxResult.success(list);
    }
}
