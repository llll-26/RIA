package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.BizActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 志愿活动/讲座Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Mapper
public interface BizActivityMapper
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
     * 删除志愿活动/讲座
     *
     * @param id 志愿活动/讲座主键
     * @return 结果
     */
    public int deleteBizActivityById(Long id);

    /**
     * 批量删除志愿活动/讲座
     ** @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizActivityByIds(Long[] ids);

    void updateCurrentParticipants(Long activityId);


    void decrementCurrentParticipants(@Param("activityId") Long activityId);

    int hideActivities(@Param("ids") Long[] ids);
}
