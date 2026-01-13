package com.ruoyi.student.member.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.student.member.domain.UserFeedback;
import com.ruoyi.student.member.service.IUserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/member/feedback")
public class FeedbackController {

    @Autowired
    private IUserFeedbackService feedbackService;

    /**
     * 提交用户反馈
     */
    @PostMapping
    public AjaxResult submit(@RequestBody UserFeedback feedback) {
        // 校验必填字段
        if (feedback.getTitle() == null || feedback.getTitle().trim().isEmpty()) {
            return AjaxResult.error("请填写反馈标题");
        }
        if (feedback.getContent() == null || feedback.getContent().trim().isEmpty()) {
            return AjaxResult.error("请填写反馈内容");
        }

        feedbackService.submitFeedback(feedback);
        return AjaxResult.success("感谢您的反馈！");
    }
}
