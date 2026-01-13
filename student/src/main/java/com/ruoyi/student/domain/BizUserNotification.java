package com.ruoyi.student.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户通知对象 biz_user_notification
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
public class BizUserNotification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 通知ID */
    @Excel(name = "通知ID")
    private Long id;

    /** 接收用户ID（关联 sys_user.user_id） */
    @Excel(name = "接收用户ID", readConverterExp = "关=联,s=ys_user.user_id")
    private Long userId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 类型：1=预约提醒，2=评价提醒，3=兑换成功等 */
    @Excel(name = "类型：1=预约提醒，2=评价提醒，3=兑换成功等")
    private Integer type;

    /** 是否已读（0=未读，1=已读） */
    @Excel(name = "是否已读", readConverterExp = "0==未读，1=已读")
    private Integer isRead;

    /** 关联订单ID（可选） */
    @Excel(name = "关联订单ID", readConverterExp = "可=选")
    private Long relatedOrderId;

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

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }

    public void setIsRead(Integer isRead) 
    {
        this.isRead = isRead;
    }

    public Integer getIsRead() 
    {
        return isRead;
    }

    public void setRelatedOrderId(Long relatedOrderId) 
    {
        this.relatedOrderId = relatedOrderId;
    }

    public Long getRelatedOrderId() 
    {
        return relatedOrderId;
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
            .append("userId", getUserId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("type", getType())
            .append("isRead", getIsRead())
            .append("relatedOrderId", getRelatedOrderId())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
