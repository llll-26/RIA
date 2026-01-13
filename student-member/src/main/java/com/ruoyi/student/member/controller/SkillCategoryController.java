package com.ruoyi.student.member.controller;
import java.util.List;

import com.ruoyi.student.member.domain.SkillCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.student.member.service.ISkillService;
@RestController
@RequestMapping("/skill/category")
public class SkillCategoryController {

    @Autowired
    private ISkillService skillService;

    /**
     * 获取所有技能分类（含提供者积分规则）
     */
    @GetMapping("/categories")
    public AjaxResult getCategories() {
        try {
            List<SkillCategory> list = skillService.getSkillCategoriesWithPoints();
            return AjaxResult.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("获取技能分类失败");
        }
    }
}
