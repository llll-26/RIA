package com.ruoyi.student.member.domain.vo;

import java.io.Serializable;

public class  SkillVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 技能ID */
    private Long id;

    /** 技能名称 */
    private String title;
    /** 技能发布者ID */
    private Long userId;
    /** 技能详情 */
    private String description;

    /** 封面图（可选，轮播可能用不到但列表会用） */
    private String coverImg;

    /** 发布者用户名（来自 sys_user.user_name） */
    private String nickName;

    /** 空闲时间（JSON字符串，前端可解析为数组） */
    private String availableTime;

    /** 每小时可获得的学习积分 */
    private Integer pointsPerHour;
    private String teachingMethod;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTeachingMethod() {
        return teachingMethod;
    }

    public void setTeachingMethod(String teachingMethod) {
        this.teachingMethod = teachingMethod;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public Integer getPointsPerHour() {
        return pointsPerHour;
    }

    public void setPointsPerHour(Integer pointsPerHour) {
        this.pointsPerHour = pointsPerHour;
    }
}
