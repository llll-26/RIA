package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.BizUserNotificationMapper;
import com.ruoyi.student.domain.BizUserNotification;
import com.ruoyi.student.service.IBizUserNotificationService;

/**
 * 用户通知Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Service
public class BizUserNotificationServiceImpl implements IBizUserNotificationService
{
    @Autowired
    private BizUserNotificationMapper bizUserNotificationMapper;

    /**
     * 查询用户通知
     *
     * @param id 用户通知主键
     * @return 用户通知
     */
    @Override
    public BizUserNotification selectBizUserNotificationById(Long id)
    {
        return bizUserNotificationMapper.selectBizUserNotificationById(id);
    }

    /**
     * 查询用户通知列表
     *
     * @param bizUserNotification 用户通知
     * @return 用户通知
     */
    @Override
    public List<BizUserNotification> selectBizUserNotificationList(BizUserNotification bizUserNotification)
    {
        return bizUserNotificationMapper.selectBizUserNotificationList(bizUserNotification);
    }

    /**
     * 新增用户通知
     *
     * @param bizUserNotification 用户通知
     * @return 结果
     */
    @Override
    public int insertBizUserNotification(BizUserNotification bizUserNotification)
    {
        return bizUserNotificationMapper.insertBizUserNotification(bizUserNotification);
    }

    /**
     * 修改用户通知
     *
     * @param bizUserNotification 用户通知
     * @return 结果
     */
    @Override
    public int updateBizUserNotification(BizUserNotification bizUserNotification)
    {
        return bizUserNotificationMapper.updateBizUserNotification(bizUserNotification);
    }

    /**
     * 批量删除用户通知
     *
     * @param ids 需要删除的用户通知主键
     * @return 结果
     */
    @Override
    public int deleteBizUserNotificationByIds(Long[] ids)
    {
        return bizUserNotificationMapper.deleteBizUserNotificationByIds(ids);
    }

    /**
     * 删除用户通知信息
     *
     * @param id 用户通知主键
     * @return 结果
     */
    @Override
    public int deleteBizUserNotificationById(Long id)
    {
        return bizUserNotificationMapper.deleteBizUserNotificationById(id);
    }

    @Override
    public Integer countUnreadByUserId(Long userId) {
        if (userId == null) {
            return 0;
        }
        return bizUserNotificationMapper.countUnreadByUserId(userId);
    }
}
