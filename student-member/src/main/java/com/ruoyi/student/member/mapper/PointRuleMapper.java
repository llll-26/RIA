package com.ruoyi.student.member.mapper;

import com.ruoyi.student.member.domain.PointRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PointRuleMapper {
    PointRule selectByCategoryIdAndRoleType(@Param("categoryId") Integer categoryId, @Param("roleType") Integer roleType);
}
