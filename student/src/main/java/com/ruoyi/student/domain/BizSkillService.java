package com.ruoyi.student.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 技能服务对象 biz_skill_service
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@TableName("biz_skill_service")
public class BizSkillService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 技能ID */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 发布者ID（关联 sys_user.user_id） */
    @Excel(name = "发布者ID", readConverterExp = "关=联,s=ys_user.user_id")
    private Long userId;

    /** 技能标题 */
    @Excel(name = "技能标题")
    private String title;

    /** 详细描述 */
    @Excel(name = "详细描述")
    private String description;

    /** 封面图 */
    @Excel(name = "封面图")
    private String coverImg;

    /** 标准分类ID（可选，关联 biz_skill_category.id） */
    @Excel(name = "标准分类ID", readConverterExp = "可=选，关联,b=iz_skill_category.id")
    private Long categoryId;

    /** 自定义分类名称（可选） */
    @Excel(name = "自定义分类名称", readConverterExp = "可=选")
    private String customCategory;

    /** 可预约时间段（JSON数组） */
    @Excel(name = "可预约时间段", readConverterExp = "J=SON数组")
    private String availableTime;

    /** 每小时可获积分 */
    @Excel(name = "每小时可获积分")
    private Long pointsPerHour;

    /** 审核状态：0=待审，1=通过，2=拒绝 */
    @Excel(name = "审核状态：0=待审，1=通过，2=拒绝")
    private Long status;

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

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId()
    {
        return categoryId;
    }

    public void setCustomCategory(String customCategory)
    {
        this.customCategory = customCategory;
    }

    public String getCustomCategory()
    {
        return customCategory;
    }

    public void setAvailableTime(String availableTime)
    {
        this.availableTime = availableTime;
    }

    public String getAvailableTime()
    {
        return availableTime;
    }

    public void setPointsPerHour(Long pointsPerHour)
    {
        this.pointsPerHour = pointsPerHour;
    }

    public Long getPointsPerHour()
    {
        return pointsPerHour;
    }

    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
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
            .append("description", getDescription())
            .append("coverImg", getCoverImg())
            .append("categoryId", getCategoryId())
            .append("customCategory", getCustomCategory())
            .append("availableTime", getAvailableTime())
            .append("pointsPerHour", getPointsPerHour())
            .append("status", getStatus())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
