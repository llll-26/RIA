package com.ruoyi.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 技能分类积分规则对象 biz_point_rule
 *
 * @author ruoyi
 * @date 2025-12-15
 */
public class BizPointRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则ID */
    private Long id;

    /** 技能分类ID */
    @Excel(name = "技能分类ID")
    private Integer categoryId;

    /** 角色类型（1=提供者, 2=学习者） */
    @Excel(name = "角色类型", readConverterExp = "1=提供者,2=学习者")
    private Integer roleType;

    /** 积分值（可负） */
    @Excel(name = "积分值")
    private Integer pointValue;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Boolean isActive;

    /** 技能分类名称（非数据库字段） */
    @Excel(name = "技能分类")
    private String categoryName;
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    // ========== Getter & Setter ==========

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setPointValue(Integer pointValue) {
        this.pointValue = pointValue;
    }

    public Integer getPointValue() {
        return pointValue;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("categoryId", getCategoryId())
                .append("roleType", getRoleType())
                .append("pointValue", getPointValue())
                .append("isActive", getIsActive())
                .toString();
    }
}
