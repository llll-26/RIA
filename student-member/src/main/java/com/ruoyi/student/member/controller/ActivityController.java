package com.ruoyi.student.member.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.student.member.domain.Enrollment;
import com.ruoyi.student.member.mapper.EnrollmentMapper;
import com.ruoyi.student.member.service.IPointAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/student/activity")
public class ActivityController {

    @Autowired
    private EnrollmentMapper enrollmentMapper;

    @Autowired
    @Resource(name = "pointAccountServiceImpl")
    private IPointAccountService pointAccountService;

    @PostMapping("/admin/complete/{enrollmentId}")
    public AjaxResult adminComplete(@PathVariable Long enrollmentId) {
        if (!SecurityUtils.hasRole("admin")) {
            return AjaxResult.error("权限不足，仅学校管理员可操作");
        }

        Enrollment enroll = enrollmentMapper.selectById(enrollmentId);
        if (enroll == null) {
            return AjaxResult.error("报名记录不存在");
        }

        if (enroll.getCompletedAt() != null) {
            return AjaxResult.success("该活动已完成，无需重复操作");
        }

        //直接查活动积分
        Long activityId = enroll.getActivityId();
        Integer points = enrollmentMapper.selectPointsRewardByActivityId(activityId);
        if (points == null || points <= 0) {
            return AjaxResult.error("该活动无有效积分奖励");
        }

        // 更新状态
        enroll.setStatus(2);
        enroll.setCompletedAt(LocalDateTime.now());
        enrollmentMapper.updateById(enroll);

        // 发放积分
        pointAccountService.addTotalPoints(enroll.getUserId(), points);

        return AjaxResult.success("活动已完成，已为学生发放 " + points + " 积分！");
    }
}
