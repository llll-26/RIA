package com.ruoyi.student.member.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.student.member.domain.Notification;
import com.ruoyi.student.member.service.NotificationService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/student/member/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
   @Autowired
   private ISysUserService userService;
    /**
     * 获取指定用户的通知列表（由前端传 userId）
     */
    @GetMapping("/list")
    public AjaxResult list(@RequestParam String userName) {
        // 1. 根据用户名（学号）查询用户
        SysUser user = userService.selectUserByUserName(userName); // 返回的是 SysUser 对象
        if (user == null) {
            return AjaxResult.error("用户不存在");
        }

        // 2. 获取 user_id（Long 类型）
        Long userId = user.getUserId(); //提取 ID

        // 3. 查询通知
        List<Notification> notifications = notificationService.selectByUserId(userId);

        return AjaxResult.success(notifications);
    }

    /**
     * 标记为已读
     */
    @PutMapping("/read/{id}")
    public AjaxResult read(@PathVariable Long id) {
        boolean success = notificationService.updateIsRead(id, 1);
        return success ? AjaxResult.success() : AjaxResult.error("更新失败");
    }
}
