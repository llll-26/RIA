package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.BizUserNotification;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户通知Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Mapper
public interface BizUserNotificationMapper
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
     * 删除用户通知
     *
     * @param id 用户通知主键
     * @return 结果
     */
    public int deleteBizUserNotificationById(Long id);

    /**
     * 批量删除用户通知
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizUserNotificationByIds(Long[] ids);
    /**
     * 统计用户未读通知数量
     */
    int countUnreadByUserId(Long userId);
}
