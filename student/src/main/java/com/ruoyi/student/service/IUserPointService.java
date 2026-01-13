package com.ruoyi.student.service;

import com.ruoyi.student.domain.BizUserPoint;

public interface IUserPointService {
    BizUserPoint selectByUserId(Long userId);

    void insertUserPoint(BizUserPoint point);

    void updateUserPoint(BizUserPoint point);
}
