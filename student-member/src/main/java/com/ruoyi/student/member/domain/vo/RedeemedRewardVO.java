package com.ruoyi.student.member.domain.vo;

import java.time.LocalDateTime;

public class RedeemedRewardVO {
    private Long redeemId;          // 兑换记录ID (biz_user_reward.id)
    private Integer rewardId;       // 奖品ID
    private String name;            // 奖品名称
    private Integer pointsRequired; // 所需积分
    private Integer pointsUsed;     // 实际消耗积分
    private String code;            // 兑换码
    private Byte status;            // 兑换状态：1=已发放,2=已使用
    private Integer quantity;       // 兑换数量
    private LocalDateTime createdAt;

    public Long getRedeemId() {
        return redeemId;
    }

    public void setRedeemId(Long redeemId) {
        this.redeemId = redeemId;
    }

    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(Integer pointsRequired) {
        this.pointsRequired = pointsRequired;
    }

    public Integer getPointsUsed() {
        return pointsUsed;
    }

    public void setPointsUsed(Integer pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
