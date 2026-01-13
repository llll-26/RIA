package com.ruoyi.student.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.student.domain.BizPointRule;
import com.ruoyi.student.mapper.BizPointRuleMapper;
import com.ruoyi.student.service.IBizPointRuleService;

@Service
public class BizPointRuleServiceImpl implements IBizPointRuleService {

    @Autowired
    private BizPointRuleMapper bizPointRuleMapper;

    @Override
    public List<BizPointRule> selectAllPointRules() {
        return bizPointRuleMapper.selectAllPointRules();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void savePointRules(List<BizPointRule> newRules) {
        // 1. 获取当前所有规则 ID
        List<BizPointRule> currentRules = bizPointRuleMapper.selectAllPointRules();
        List<Long> currentIds = currentRules.stream()
                .map(BizPointRule::getId)
                .collect(Collectors.toList());

        // 2. 新规则中的有效 ID（已存在的）
        Set<Long> newIds = newRules.stream()
                .filter(r -> r.getId() != null && r.getId() > 0)
                .map(BizPointRule::getId)
                .collect(Collectors.toSet());

        // 3. 找出要删除的 ID：存在但不在新列表中
        List<Long> toDelete = currentIds.stream()
                .filter(id -> !newIds.contains(id))
                .collect(Collectors.toList());

        // 4. 删除多余规则
        if (!toDelete.isEmpty()) {
            bizPointRuleMapper.deleteByIds(toDelete);
        }

        // 5. 保存/更新新规则
        for (BizPointRule rule : newRules) {
            if (rule.getId() == null || rule.getId() <= 0) {
                // 新增
                bizPointRuleMapper.insertPointRule(rule);
            } else {
                // 更新
                bizPointRuleMapper.updatePointRule(rule);
            }
        }
    }
}
