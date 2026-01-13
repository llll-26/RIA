package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.BizActivityMapper;
import com.ruoyi.student.domain.BizActivity;
import com.ruoyi.student.service.IBizActivityService;

/**
 * 志愿活动/讲座Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Service
public class BizActivityServiceImpl implements IBizActivityService
{
    @Autowired
    private BizActivityMapper bizActivityMapper;

    /**
     * 查询志愿活动/讲座
     *
     * @param id 志愿活动/讲座主键
     * @return 志愿活动/讲座
     */
    @Override
    public BizActivity selectBizActivityById(Long id)
    {
        return bizActivityMapper.selectBizActivityById(id);
    }

    /**
     * 查询志愿活动/讲座列表
     *
     * @param bizActivity 志愿活动/讲座
     * @return 志愿活动/讲座
     */
    @Override
    public List<BizActivity> selectBizActivityList(BizActivity bizActivity)
    {
        return bizActivityMapper.selectBizActivityList(bizActivity);
    }

    /**
     * 新增志愿活动/讲座
     *
     * @param bizActivity 志愿活动/讲座
     * @return 结果
     */
    @Override
    public int insertBizActivity(BizActivity bizActivity)
    {
        return bizActivityMapper.insertBizActivity(bizActivity);
    }

    /**
     * 修改志愿活动/讲座
     *
     * @param bizActivity 志愿活动/讲座
     * @return 结果
     */
    @Override
    public int updateBizActivity(BizActivity bizActivity)
    {
        return bizActivityMapper.updateBizActivity(bizActivity);
    }

    /**
     * 批量删除志愿活动/讲座
     *
     * @param ids 需要删除的志愿活动/讲座主键
     * @return 结果
     */
    @Override
    public int deleteBizActivityByIds(Long[] ids)
    {
        return bizActivityMapper.deleteBizActivityByIds(ids);
    }

    /**
     * 删除志愿活动/讲座信息
     *
     * @param id 志愿活动/讲座主键
     * @return 结果
     */
    @Override
    public int deleteBizActivityById(Long id)
    {
        return bizActivityMapper.deleteBizActivityById(id);
    }

    // 实现类
    @Override
    public int hideActivities(Long[] ids) {
        return bizActivityMapper.hideActivities(ids); // 不再传 status，由 SQL 固定
    }
}
