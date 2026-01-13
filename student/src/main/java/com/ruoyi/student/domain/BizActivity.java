package com.ruoyi.student.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 志愿活动/讲座对象 biz_activity
 *
 * @author ruoyi
 * @date 2025-12-15
 */
public class BizActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动ID */
    @Excel(name = "活动ID")
    private Long id;

    /** 活动标题 */
    @Excel(name = "活动标题")
    private String title;

    /** 活动详情描述 */
    @Excel(name = "活动详情描述")
    private String description;

    /** 活动封面图 */
    @Excel(name = "活动封面图")
    private String coverImg;

    /** 活动开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 活动结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 活动地点（线下）或会议链接（线上） */
    @Excel(name = "活动地点", readConverterExp = "线=下")
    private String location;

    /** 最大参与人数（-1 表示不限） */
    @Excel(name = "最大参与人数", readConverterExp = "-=1,表=示不限")
    private Long maxParticipants;

    /** 当前已报名人数 */
    @Excel(name = "当前已报名人数")
    private Long currentParticipants;

    /** 参与奖励积分（可选） */
    @Excel(name = "参与奖励积分", readConverterExp = "可=选")
    private Long pointsReward;

    /** 状态：0=已结束，1=进行中，2=已取消 */
    @Excel(name = "状态：0=已结束，1=进行中，2=已取消,3= 已下架（管理员隐藏）")
    private Long status;

    /** 组织者ID（管理员或社团，关联 sys_user.user_id） */
    @Excel(name = "组织者ID", readConverterExp = "管=理员或社团，关联,s=ys_user.user_id")
    private Long organizerId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "活动开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setCoverImg(String coverImg)
    {
        this.coverImg = coverImg;
    }

    public String getCoverImg()
    {
        return coverImg;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }

    public void setMaxParticipants(Long maxParticipants)
    {
        this.maxParticipants = maxParticipants;
    }

    public Long getMaxParticipants()
    {
        return maxParticipants;
    }

    public void setCurrentParticipants(Long currentParticipants)
    {
        this.currentParticipants = currentParticipants;
    }

    public Long getCurrentParticipants()
    {
        return currentParticipants;
    }

    public void setPointsReward(Long pointsReward)
    {
        this.pointsReward = pointsReward;
    }

    public Long getPointsReward()
    {
        return pointsReward;
    }

    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }

    public void setOrganizerId(Long organizerId)
    {
        this.organizerId = organizerId;
    }

    public Long getOrganizerId()
    {
        return organizerId;
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
            .append("title", getTitle())
            .append("description", getDescription())
            .append("coverImg", getCoverImg())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("location", getLocation())
            .append("maxParticipants", getMaxParticipants())
            .append("currentParticipants", getCurrentParticipants())
            .append("pointsReward", getPointsReward())
            .append("status", getStatus())
            .append("organizerId", getOrganizerId())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
