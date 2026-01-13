package com.ruoyi.student.service;

import java.util.List;
import com.ruoyi.student.domain.BizUserNotification;

/**
 * 用户通知Service接口
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
public interface IBizUserNotificationService 
{
    /**
     * 查询用户通知
     * 
     * @param id 用户通知主键
     * @return 用户通知
     */
    public BizUserNotification selectBizUserNotificationById(Long id);

    /**
     * 查询用户通知列表
     * 
     * @param bizUserNotification 用户通知
     * @return 用户通知集合
     */
    public List<BizUserNotification> selectBizUserNotificationList(BizUserNotification bizUserNotification);

    /**
     * 新增用户通知
     * 
     * @param bizUserNotification 用户通知
     * @return 结果
     */
    public int insertBizUserNotification(BizUserNotification bizUserNotification);

    /**
     * 修改用户通知
     * 
     * @param bizUserNotification 用户通知
     * @return 结果
     */
    public int updateBizUserNotification(BizUserNotification bizUserNotification);

    /**
     * 批量删除用户通知
     * 
     * @param ids 需要删除的用户通知主键集合
     * @return 结果
     */
    public int deleteBizUserNotificationByIds(Long[] ids);

    /**
     * 删除用户通知信息
     * 
     * @param id 用户通知主键
     * @return 结果
     */
    public int deleteBizUserNotificationById(Long id);

    Integer countUnreadByUserId(Long currentUserId);
}
