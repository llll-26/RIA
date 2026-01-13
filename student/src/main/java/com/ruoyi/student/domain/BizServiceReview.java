package com.ruoyi.student.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务评价对象 biz_service_review
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
public class BizServiceReview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价ID */
    @Excel(name = "评价ID")
    private Long id;

    /** 关联订单ID（一对一） */
    @Excel(name = "关联订单ID", readConverterExp = "一=对一")
    private Long orderId;

    /** 评价人ID（关联 sys_user.user_id） */
    @Excel(name = "评价人ID", readConverterExp = "关=联,s=ys_user.user_id")
    private Long reviewerId;

    /** 被评价人ID（关联 sys_user.user_id） */
    @Excel(name = "被评价人ID", readConverterExp = "关=联,s=ys_user.user_id")
    private Long reviewedId;

    /** 评分（1-5星） */
    @Excel(name = "评分", readConverterExp = "1=-5星")
    private Long rating;

    /** 文字评价 */
    @Excel(name = "文字评价")
    private String comment;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }

    public void setReviewerId(Long reviewerId) 
    {
        this.reviewerId = reviewerId;
    }

    public Long getReviewerId() 
    {
        return reviewerId;
    }

    public void setReviewedId(Long reviewedId) 
    {
        this.reviewedId = reviewedId;
    }

    public Long getReviewedId() 
    {
        return reviewedId;
    }

    public void setRating(Long rating) 
    {
        this.rating = rating;
    }

    public Long getRating() 
    {
        return rating;
    }

    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("reviewerId", getReviewerId())
            .append("reviewedId", getReviewedId())
            .append("rating", getRating())
            .append("comment", getComment())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
