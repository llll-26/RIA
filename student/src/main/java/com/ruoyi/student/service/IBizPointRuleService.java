package com.ruoyi.student.service;

import java.util.List;
import com.ruoyi.student.domain.BizPointRule;

/**
 * 积分规则Service接口
 *
 * @author ruoyi
 * @date 2025-12-15
 */
public interface IBizPointRuleService
{
    /**
     * 获取所有积分规则（用于前端配置页）
     */
    List<BizPointRule> selectAllPointRules();

    /**
     * 批量保存或更新积分规则（自动 upsert）
     */
    void savePointRules(List<BizPointRule> rules);



}
