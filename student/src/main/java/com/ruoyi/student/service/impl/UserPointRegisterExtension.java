package com.ruoyi.student.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.framework.web.service.IUserRegisterExtension;
import com.ruoyi.student.domain.BizUserPoint;
import com.ruoyi.student.service.IUserPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // ← 让 Spring 管理这个 Bean
public class UserPointRegisterExtension implements IUserRegisterExtension {

    @Autowired
    private IUserPointService userPointService;

    @Override
    public void afterRegister(SysUser user) {
        // 自动为新用户创建积分账户
        BizUserPoint point = new BizUserPoint();
        point.setUserId(user.getUserId());
        point.setTotalPoints(0);
        point.setUsedPoints(0);
        userPointService.insertUserPoint(point);
    }
}
