package com.ruoyi.student.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户反馈对象 biz_user_feedback
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
public class BizUserFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 反馈ID */
    @Excel(name = "反馈ID")
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 反馈标题 */
    @Excel(name = "反馈标题")
    private String title;

    /** 详细内容 */
    @Excel(name = "详细内容")
    private String content;

    /** 联系方式（选填） */
    @Excel(name = "联系方式", readConverterExp = "选=填")
    private String contact;

    /** 处理状态：0=未处理，1=已处理 */
    @Excel(name = "处理状态：0=未处理，1=已处理")
    private Long status;

    /** 处理完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date processedAt;

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

    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setProcessedAt(Date processedAt) 
    {
        this.processedAt = processedAt;
    }

    public Date getProcessedAt() 
    {
        return processedAt;
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
            .append("contact", getContact())
            .append("status", getStatus())
            .append("processedAt", getProcessedAt())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
