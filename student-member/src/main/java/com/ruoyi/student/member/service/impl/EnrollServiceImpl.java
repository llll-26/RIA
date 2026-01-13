package com.ruoyi.student.member.service.impl;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.student.domain.BizActivity;
import com.ruoyi.student.mapper.BizActivityMapper;
import com.ruoyi.student.member.domain.Enrollment;
import com.ruoyi.student.member.mapper.EnrollmentMapper;
import com.ruoyi.student.member.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EnrollServiceImpl implements EnrollService {

    @Autowired
    private BizActivityMapper activityMapper;

    @Autowired
    private EnrollmentMapper enrollmentMapper;

    @Override
    public boolean isEnrolled(Long userId, Long activityId) {
        return enrollmentMapper.existsByUserIdAndActivityId(userId, activityId);
    }

    @Override
    public List<BizActivity> getCompletedActivities(Long userId) {
        //查询用户所有 completed_at 非空的报名
        List<Enrollment> enrollments = enrollmentMapper.selectCompletedByUserId(userId);

        // 关联活动信息
        return enrollments.stream()
                .map(e -> activityMapper.selectBizActivityById(e.getActivityId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void enroll(Long activityId) {
        Long userId = SecurityUtils.getLoginUser().getUserId();

        //查活动是否存在
        BizActivity activity = activityMapper.selectBizActivityById(activityId);
        if (activity == null) {
            throw new RuntimeException("活动不存在");
        }

        //检查是否已过报名截止时间（截止=活动开始时间）
        Date now = new Date();
        if (now.compareTo(activity.getStartTime()) >= 0) {
            throw new RuntimeException("报名已截止");
        }

        //检查是否已报名
        if (isEnrolled(userId, activityId)) {
            throw new RuntimeException("您已报名该活动");
        }

        //检查人数是否已满（-1 表示不限）
        if (!Long.valueOf(-1).equals(activity.getMaxParticipants()) &&
                activity.getCurrentParticipants() >= activity.getMaxParticipants()) {
            throw new RuntimeException("活动人数已满");
        }

        //插入报名记录
        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(userId);
        enrollment.setActivityId(activityId);
        enrollment.setStatus(0); // 已报名
        enrollmentMapper.insert(enrollment);

        //更新活动当前参与人数
        activityMapper.updateCurrentParticipants(activityId);
    }


}
