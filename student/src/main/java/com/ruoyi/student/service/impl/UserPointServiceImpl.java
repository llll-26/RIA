package com.ruoyi.student.service.impl;

import com.ruoyi.student.domain.BizUserPoint;
import com.ruoyi.student.mapper.BizUserPointMapper;
import com.ruoyi.student.service.IUserPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPointServiceImpl implements IUserPointService {

    @Autowired
    private BizUserPointMapper bizUserPointMapper;

    @Override
    public BizUserPoint selectByUserId(Long userId) {
        return bizUserPointMapper.selectByUserId(userId);
    }

    @Override
    public void insertUserPoint(BizUserPoint point) {
        bizUserPointMapper.insert(point);
    }

    @Override
    public void updateUserPoint(BizUserPoint point) {
        bizUserPointMapper.update(point);
    }
}
