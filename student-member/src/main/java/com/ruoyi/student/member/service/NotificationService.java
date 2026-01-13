package com.ruoyi.student.member.service;


import com.ruoyi.student.member.domain.Notification;


import java.util.List;


public interface NotificationService {


    List<Notification> selectByUserId(Long userId);

    boolean updateIsRead(Long id, int i);
}
