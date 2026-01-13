package com.ruoyi.student.member.service;

import com.ruoyi.student.domain.BizActivity;

import java.util.List;

public interface EnrollService {
    void enroll(Long activityId);
    boolean isEnrolled(Long userId, Long activityId);

    List<BizActivity> getCompletedActivities(Long userId);

}
