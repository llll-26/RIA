package com.ruoyi.student.member.service.impl;

import com.ruoyi.student.member.domain.Notification;
import com.ruoyi.student.member.mapper.NotificationMapper;
import com.ruoyi.student.member.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public List<Notification> selectByUserId(Long userId) {
        return notificationMapper.selectByUserId(userId);
    }

    @Override
    public boolean updateIsRead(Long id, int isRead) {
        return notificationMapper.updateIsRead(id, isRead) > 0;
    }
}
