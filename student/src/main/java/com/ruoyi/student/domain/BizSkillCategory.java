package com.ruoyi.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 技能标准分类对象 biz_skill_category
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public class BizSkillCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID */
    @Excel(name = "分类ID")
    private Long id;

    /** 分类名称（如：编程、设计、外语） */
    @Excel(name = "分类名称", readConverterExp = "如=：编程、设计、外语")
    private String name;

    /** 排序权重 */
    @Excel(name = "排序权重")
    private Long sort;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private Integer isActive;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }

    public void setIsActive(Integer isActive) 
    {
        this.isActive = isActive;
    }

    public Integer getIsActive() 
    {
        return isActive;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("sort", getSort())
            .append("isActive", getIsActive())
            .toString();
    }
}
