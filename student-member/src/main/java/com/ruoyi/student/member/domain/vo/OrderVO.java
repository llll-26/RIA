package com.ruoyi.student.member.domain.vo;

import java.time.LocalDateTime;

public class OrderVO {
    private Long id;
    private Long requesterId;
    private Long providerId;
    private String skillTitle;
    private String providerNickName;
    private String requesterNickName;
    private String coverImg;
    private LocalDateTime appointmentTime;
    private String description;
    private Integer status;
    private Integer learnerPoints;
    private Integer providerPoints;
    private String locationDetail;
    private String proofUrl;
    private String providerPhone;
    private String teachingMethod;
    private Integer hasCommented;
    private Integer score;
    private String comment;
    private Boolean isRead;
    private String providerAvatar;
    private String requesterAvatar;
    private Long skillId;

    // getter / setter
    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
    public String getProviderAvatar() {
        return providerAvatar;
    }

    public void setProviderAvatar(String providerAvatar) {
        this.providerAvatar = providerAvatar;
    }

    public String getRequesterAvatar() {
        return requesterAvatar;
    }

    public void setRequesterAvatar(String requesterAvatar) {
        this.requesterAvatar = requesterAvatar;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Integer getHasCommented() {
        return hasCommented;
    }

    public void setHasCommented(Integer hasCommented) {
        this.hasCommented = hasCommented;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public void setTeachingMethod(String teachingMethod) {
        this.teachingMethod = teachingMethod;
    }

    public String getProofUrl() {
        return proofUrl;
    }

    public void setProofUrl(String proofUrl) {
        this.proofUrl = proofUrl;
    }

    public String getProviderPhone() {
        return providerPhone;
    }

    public void setProviderPhone(String providerPhone) {
        this.providerPhone = providerPhone;
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

    public String getRequesterNickName() {
        return requesterNickName;
    }

    public void setRequesterNickName(String requesterNickName) {
        this.requesterNickName = requesterNickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }

    public String getProviderNickName() {
        return providerNickName;
    }

    public void setProviderNickName(String providerNickName) {
        this.providerNickName = providerNickName;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }


}
