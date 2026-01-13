package com.ruoyi.student.controller.point;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.student.domain.BizPointRule;
import com.ruoyi.student.service.IBizPointRuleService;

/**
 * 积分规则配置Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@RestController
@RequestMapping("/student/point/rule")
public class BizPointRuleController extends BaseController
{
    @Autowired
    private IBizPointRuleService bizPointRuleService;

    /**
     * 获取所有积分规则（用于配置页面）
     */
    @PreAuthorize("@ss.hasPermi('student:pointRule:config')")
    @GetMapping("/all")
    public AjaxResult all()
    {
        List<BizPointRule> list = bizPointRuleService.selectAllPointRules();
        return success(list);
    }

    /**
     * 批量保存积分规则
     */
    @PreAuthorize("@ss.hasPermi('student:pointRule:config')")
    @Log(title = "积分规则配置", businessType = BusinessType.UPDATE)
    @PostMapping("/save")
    public AjaxResult save(@RequestBody List<BizPointRule> rules)
    {
        bizPointRuleService.savePointRules(rules);
        return success();
    }

}
