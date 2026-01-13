package com.ruoyi.student.member.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.student.member.domain.UserFeedback;
import com.ruoyi.student.member.mapper.UserFeedbackMapper;
import com.ruoyi.student.member.service.IUserFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFeedbackServiceImpl implements IUserFeedbackService {

    @Autowired
    private UserFeedbackMapper feedbackMapper;

    @Override
    public void submitFeedback(UserFeedback feedback) {
        feedback.setUserId(SecurityUtils.getUserId());
        feedbackMapper.insert(feedback);
    }
}
