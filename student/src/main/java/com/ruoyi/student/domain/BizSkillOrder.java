package com.ruoyi.student.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 技能订单对象 biz_skill_order
 *
 * @author ruoyi
 * @date 2025-12-27
 */
public class BizSkillOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    @Excel(name = "订单ID")
    private Long id;

    /** 技能ID（关联 biz_skill_service.id） */
    @Excel(name = "技能ID", readConverterExp = "关=联,b=iz_skill_service.id")
    private Long skillId;

    /** 请求者ID（学习者，关联 sys_user.user_id） */
    @Excel(name = "请求者ID", readConverterExp = "学=习者，关联,s=ys_user.user_id")
    private Long requesterId;

    /** 提供者ID（技能达人，关联 sys_user.user_id） */
    @Excel(name = "提供者ID", readConverterExp = "技=能达人，关联,s=ys_user.user_id")
    private Long providerId;

    /** 预约服务时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约服务时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date appointmentTime;

    /** 服务形式：0=线上，1=线下 */
    @Excel(name = "服务形式：0=线上，1=线下")
    private Long locationType;

    /** 具体地点或会议链接 */
    @Excel(name = "具体地点或会议链接")
    private String locationDetail;

    /** 0-待确认,1-已同意,2-已拒绝,3-已提交凭证,4-已完成 */
    @Excel(name = "0-待确认,1-已同意,2-已拒绝,3-已提交凭证,4-已完成")
    private Long status;

    /** 是否已生成服务证明 */
    @Excel(name = "是否已生成服务证明")
    private Integer proofGenerated;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 预约时长（单位：小时） */
    @Excel(name = "预约时长", readConverterExp = "单=位：小时")
    private Long durationHours;

    /** 完成凭证（base64 或 URL） */
    @Excel(name = "完成凭证", readConverterExp = "b=ase64,或=,U=RL")
    private String proofUrl;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setSkillId(Long skillId)
    {
        this.skillId = skillId;
    }

    public Long getSkillId()
    {
        return skillId;
    }

    public void setRequesterId(Long requesterId)
    {
        this.requesterId = requesterId;
    }

    public Long getRequesterId()
    {
        return requesterId;
    }

    public void setProviderId(Long providerId)
    {
        this.providerId = providerId;
    }

    public Long getProviderId()
    {
        return providerId;
    }

    public void setAppointmentTime(Date appointmentTime)
    {
        this.appointmentTime = appointmentTime;
    }

    public Date getAppointmentTime()
    {
        return appointmentTime;
    }

    public void setLocationType(Long locationType)
    {
        this.locationType = locationType;
    }

    public Long getLocationType()
    {
        return locationType;
    }

    public void setLocationDetail(String locationDetail)
    {
        this.locationDetail = locationDetail;
    }

    public String getLocationDetail()
    {
        return locationDetail;
    }

    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }

    public void setProofGenerated(Integer proofGenerated)
    {
        this.proofGenerated = proofGenerated;
    }

    public Integer getProofGenerated()
    {
        return proofGenerated;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setDurationHours(Long durationHours)
    {
        this.durationHours = durationHours;
    }

    public Long getDurationHours()
    {
        return durationHours;
    }

    public void setProofUrl(String proofUrl)
    {
        this.proofUrl = proofUrl;
    }

    public String getProofUrl()
    {
        return proofUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("skillId", getSkillId())
            .append("requesterId", getRequesterId())
            .append("providerId", getProviderId())
            .append("appointmentTime", getAppointmentTime())
            .append("locationType", getLocationType())
            .append("locationDetail", getLocationDetail())
            .append("status", getStatus())
            .append("proofGenerated", getProofGenerated())
            .append("createdAt", getCreatedAt())
            .append("durationHours", getDurationHours())
            .append("proofUrl", getProofUrl())
            .toString();
    }
}
