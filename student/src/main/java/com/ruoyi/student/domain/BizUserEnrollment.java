package com.ruoyi.student.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户活动报名记录对象 biz_user_enrollment
 *
 * @author ruoyi
 * @date 2025-12-15
 */
public class BizUserEnrollment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报名ID */
    @Excel(name = "报名ID")
    private Long id;

    /** 报名用户ID（关联 sys_user.user_id） */
    @Excel(name = "报名用户ID", readConverterExp = "关=联,s=ys_user.user_id")
    private Long userId;

    /** 活动ID（关联 biz_activity.id） */
    @Excel(name = "活动ID", readConverterExp = "关=联,b=iz_activity.id")
    private Long activityId;

    /** 报名状态：0=已报名，1=已取消，2=已完成（参与） */
    @Excel(name = "报名状态：0=已报名，1=已取消，2=已完成", readConverterExp = "参=与")
    private Long status;

    /** 报名时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "报名时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enrolledAt;


    /** 完成时间（用于发放积分） */
    @Excel(name = "完成时间", readConverterExp = "用=于发放积分")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date completedAt;

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

    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    public Long getActivityId()
    {
        return activityId;
    }

    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }

    public void setEnrolledAt(Date enrolledAt)
    {
        this.enrolledAt = enrolledAt;
    }

    public Date getEnrolledAt()
    {
        return enrolledAt;
    }

    public void setCompletedAt(Date completedAt)
    {
        this.completedAt = completedAt;
    }

    public Date getCompletedAt()
    {
        return completedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("activityId", getActivityId())
            .append("status", getStatus())
            .append("enrolledAt", getEnrolledAt())
            .append("completedAt", getCompletedAt())
            .toString();
    }
}
