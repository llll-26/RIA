package com.ruoyi.student.member.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.student.domain.BizActivity;
import com.ruoyi.student.mapper.BizActivityMapper;
import com.ruoyi.student.member.domain.Enrollment;
import com.ruoyi.student.member.mapper.EnrollmentMapper;
import com.ruoyi.student.member.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ruoyi.common.core.domain.AjaxResult.success;

@RestController
@RequestMapping("/student/member/enroll")
public class EnrollController {

    @Autowired
    private EnrollService enrollService;
    @Autowired
    private BizActivityMapper activityMapper;
    @Autowired
    private EnrollmentMapper enrollmentMapper;

    @PostMapping("/{activityId}")
    public AjaxResult enroll(@PathVariable Long activityId) {
        enrollService.enroll(activityId);
        return success("报名成功");
    }

    @GetMapping("/check/{activityId}")
    public AjaxResult check(@PathVariable Long activityId) {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        boolean enrolled = enrollService.isEnrolled(userId, activityId);
        return success(enrolled);
    }

    @GetMapping("/{id}")
    public AjaxResult getDetail(@PathVariable Long id) {
        // 查活动
        BizActivity activity = activityMapper.selectBizActivityById(id);
        if (activity == null) {
            return AjaxResult.error("活动不存在");
        }

        //获取当前登录用户ID
        Long userId = SecurityUtils.getLoginUser().getUserId();

        //查询是否已报名
        boolean isEnrolled = enrollService.isEnrolled(userId, id);

        //利用 params 携带非数据库字段（若依常用技巧）
        activity.getParams().put("isEnrolled", isEnrolled);

        return success(activity);
    }



    @GetMapping("/my/completed")
    public AjaxResult getMyCompletedActivities() {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        List<BizActivity> completedActivities = enrollService.getCompletedActivities(userId);
        return success(completedActivities);
    }
    @GetMapping("/my/page")
    public AjaxResult getMyEnrollmentsPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        Long userId = SecurityUtils.getLoginUser().getUserId();

        int offset = (pageNum - 1) * pageSize;
        List<Enrollment> enrollments = enrollmentMapper.selectByUserIdWithPagination(userId, offset, pageSize);
        int total = enrollmentMapper.countByUserId(userId);

        List<BizActivity> activities = new ArrayList<>();
        for (Enrollment e : enrollments) {
            BizActivity activity = activityMapper.selectBizActivityById(e.getActivityId());
            if (activity != null) {
                activity.getParams().put("enrollmentId", e.getId());
                activity.getParams().put("enrolledAt", e.getEnrolledAt());
                activity.getParams().put("completedAt", e.getCompletedAt());
                activity.getParams().put("isEnrolled", true);
                activities.add(activity);
            }
        }
        return success(Map.of("total", total, "list", activities));
    }
}
