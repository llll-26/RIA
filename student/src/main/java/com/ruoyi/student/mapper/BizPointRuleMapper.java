package com.ruoyi.student.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.student.domain.BizPointRule;
@Mapper
public interface BizPointRuleMapper {

    /**
     * 查询所有积分规则
     */
    List<BizPointRule> selectAllPointRules();

    /**
     * 根据 ID 批量删除
     */
    int deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 插入一条规则
     */
    int insertPointRule(BizPointRule rule);

    /**
     * 更新一条规则
     */
    int updatePointRule(BizPointRule rule);

    /**
     * 根据 ID 查询（可选，用于校验）
     */
    BizPointRule selectById(Long id);
}
