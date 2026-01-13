package com.ruoyi.student.member.domain;

import java.time.LocalDateTime;

/**
 * 技能订单实体类
 * 对应数据库表：biz_skill_order
 */

public class SkillOrder {
    /**
     * 订单ID
     */
    private Long id;

    /**
     * 技能ID（关联 biz_skill_service.id）
     */
    private Long skillId;

    /**
     * 请求者ID（学习者，关联 sys_user.user_id）
     */
    private Long requesterId;

    /**
     * 提供者ID（技能达人，关联 sys_user.user_id）
     */
    private Long providerId;

    /**
     * 预约服务时间
     */
    private LocalDateTime appointmentTime;
    /** 预约时长（单位：小时） */
    private Integer durationHours;

    /**
     * 服务形式：0=线上，1=线下
     */
    private Byte locationType;

    /**
     * 具体地点或会议链接
     */
    private String locationDetail;
    private String proofUrl;
    /**
     * 状态：0=待确认，1=已确认，2=已完成，3=已取消
     */
    private Integer status;

    /**
     * 是否已生成服务证明
     */
    private Boolean proofGenerated;
    private Integer hasCommented;
    /** 下单人昵称（学员） */
    private String requesterNickName;

    /** 提供者昵称（老师） */
    private String providerNickName;

    private Integer score;          // 评分（1~5）
    private String comment;  // 评论内容
    private String skillTitle;
    /** 学习者获得的积分（用于已完成订单） */
    private Integer learnerPoints;

    /** 提供者获得的积分（用于已完成订单） */
    private Integer providerPoints;
    private Boolean isRead;

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Integer getLearnerPoints() {
        return learnerPoints;
    }

    public void setLearnerPoints(Integer learnerPoints) {
        this.learnerPoints = learnerPoints;
    }

    public Integer getProviderPoints() {
        return providerPoints;
    }

    public void setProviderPoints(Integer providerPoints) {
        this.providerPoints = providerPoints;
    }

    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getRequesterNickName() {
        return requesterNickName;
    }

    public void setRequesterNickName(String requesterNickName) {
        this.requesterNickName = requesterNickName;
    }

    public String getProviderNickName() {
        return providerNickName;
    }

    public void setProviderNickName(String providerNickName) {
        this.providerNickName = providerNickName;
    }
    public Integer getHasCommented() {
        return hasCommented;
    }

    public void setHasCommented(Integer hasCommented) {
        this.hasCommented = hasCommented;
    }

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    public String getProofUrl() {
        return proofUrl;
    }

    public void setProofUrl(String proofUrl) {
        this.proofUrl = proofUrl;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Byte getLocationType() {
        return locationType;
    }

    public void setLocationType(Byte locationType) {
        this.locationType = locationType;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getProofGenerated() {
        return proofGenerated;
    }

    public void setProofGenerated(Boolean proofGenerated) {
        this.proofGenerated = proofGenerated;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
