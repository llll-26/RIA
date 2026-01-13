package com.ruoyi.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 兑换奖品项对象 biz_reward_item
 * 
 * @author ruoyi
 * @date 2025-12-27
 */
public class BizRewardItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 奖品ID */
    @Excel(name = "奖品ID")
    private Long id;

    /** 奖品名称 */
    @Excel(name = "奖品名称")
    private String name;

    /** 所需积分 */
    @Excel(name = "所需积分")
    private Long pointsRequired;

    /** 类型：1=实物，2=权益，3=荣誉 */
    @Excel(name = "类型：1=实物，2=权益，3=荣誉")
    private Long type;

    /** 库存（-1表示无限） */
    @Excel(name = "库存", readConverterExp = "-=1表示无限")
    private Long stock;

    /** 是否上架 */
    @Excel(name = "是否上架")
    private Integer isActive;

    /** 详细说明 */
    @Excel(name = "详细说明")
    private String description;

    /** 奖品图片URL */
    @Excel(name = "奖品图片URL")
    private String imageUrl;

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

    public void setPointsRequired(Long pointsRequired) 
    {
        this.pointsRequired = pointsRequired;
    }

    public Long getPointsRequired() 
    {
        return pointsRequired;
    }

    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }

    public void setStock(Long stock) 
    {
        this.stock = stock;
    }

    public Long getStock() 
    {
        return stock;
    }

    public void setIsActive(Integer isActive) 
    {
        this.isActive = isActive;
    }

    public Integer getIsActive() 
    {
        return isActive;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("pointsRequired", getPointsRequired())
            .append("type", getType())
            .append("stock", getStock())
            .append("isActive", getIsActive())
            .append("description", getDescription())
            .append("imageUrl", getImageUrl())
            .toString();
    }
}
