package com.ruoyi.student.member.domain.vo;
import java.time.LocalDateTime;
//表示某次获得积分的记录
public class PointRecordVO {
    private Long orderId;
    private String skillTitle;
    private LocalDateTime appointmentTime;
    private Integer points;
    private String type;


    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getSkillTitle() { return skillTitle; }
    public void setSkillTitle(String skillTitle) { this.skillTitle = skillTitle; }

    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
