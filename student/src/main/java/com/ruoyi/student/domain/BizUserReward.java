package com.ruoyi.student.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户兑换记录对象 biz_user_reward
 * 
 * @author ruoyi
 * @date 2025-12-27
 */
public class BizUserReward extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 兑换ID */
    @Excel(name = "兑换ID")
    private Long id;

    /** 用户ID（关联 sys_user.user_id） */
    @Excel(name = "用户ID", readConverterExp = "关=联,s=ys_user.user_id")
    private Long userId;

    /** 奖品ID（关联 biz_reward_item.id） */
    @Excel(name = "奖品ID", readConverterExp = "关=联,b=iz_reward_item.id")
    private Long rewardId;

    /** 消耗积分 */
    @Excel(name = "消耗积分")
    private Long pointsUsed;

    /** 兑换码（如打印券编号） */
    @Excel(name = "兑换码", readConverterExp = "如=打印券编号")
    private String code;

    /** 状态：1=已发放，2=已使用 */
    @Excel(name = "状态：1=已发放，2=已使用")
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

    public void setRewardId(Long rewardId) 
    {
        this.rewardId = rewardId;
    }

    public Long getRewardId() 
    {
        return rewardId;
    }

    public void setPointsUsed(Long pointsUsed) 
    {
        this.pointsUsed = pointsUsed;
    }

    public Long getPointsUsed() 
    {
        return pointsUsed;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
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
            .append("rewardId", getRewardId())
            .append("pointsUsed", getPointsUsed())
            .append("code", getCode())
            .append("status", getStatus())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
