package com.ruoyi.student.service;

import java.util.List;
import com.ruoyi.student.domain.BizActivity;

/**
 * 志愿活动/讲座Service接口
 *
 * @author ruoyi
 * @date 2025-12-15
 */
public interface IBizActivityService
{
    /**
     * 查询志愿活动/讲座
     *
     * @param id 志愿活动/讲座主键
     * @return 志愿活动/讲座
     */
    public BizActivity selectBizActivityById(Long id);

    /**
     * 查询志愿活动/讲座列表
     *
     * @param bizActivity 志愿活动/讲座
     * @return 志愿活动/讲座集合
     */
    public List<BizActivity> selectBizActivityList(BizActivity bizActivity);

    /**
     * 新增志愿活动/讲座
     *
     * @param bizActivity 志愿活动/讲座
     * @return 结果
     */
    public int insertBizActivity(BizActivity bizActivity);

    /**
     * 修改志愿活动/讲座
     *
     * @param bizActivity 志愿活动/讲座
     * @return 结果
     */
    public int updateBizActivity(BizActivity bizActivity);

    /**
     * 批量删除志愿活动/讲座
     *
     * @param ids 需要删除的志愿活动/讲座主键集合
     * @return 结果
     */
    public int deleteBizActivityByIds(Long[] ids);

    /**
     * 删除志愿活动/讲座信息
     *
     、、* @param id 志愿活动/讲座主键
     * @return 结果
     */
    public int deleteBizActivityById(Long id);


    int hideActivities(Long[] ids);
}
