package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.BizUserFeedbackMapper;
import com.ruoyi.student.domain.BizUserFeedback;
import com.ruoyi.student.service.IBizUserFeedbackService;

/**
 * 用户反馈Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
@Service
public class BizUserFeedbackServiceImpl implements IBizUserFeedbackService 
{
    @Autowired
    private BizUserFeedbackMapper bizUserFeedbackMapper;

    /**
     * 查询用户反馈
     * 
     * @param id 用户反馈主键
     * @return 用户反馈
     */
    @Override
    public BizUserFeedback selectBizUserFeedbackById(Long id)
    {
        return bizUserFeedbackMapper.selectBizUserFeedbackById(id);
    }

    /**
     * 查询用户反馈列表
     * 
     * @param bizUserFeedback 用户反馈
     * @return 用户反馈
     */
    @Override
    public List<BizUserFeedback> selectBizUserFeedbackList(BizUserFeedback bizUserFeedback)
    {
        return bizUserFeedbackMapper.selectBizUserFeedbackList(bizUserFeedback);
    }

    /**
     * 新增用户反馈
     * 
     * @param bizUserFeedback 用户反馈
     * @return 结果
     */
    @Override
    public int insertBizUserFeedback(BizUserFeedback bizUserFeedback)
    {
        return bizUserFeedbackMapper.insertBizUserFeedback(bizUserFeedback);
    }

    /**
     * 修改用户反馈
     * 
     * @param bizUserFeedback 用户反馈
     * @return 结果
     */
    @Override
    public int updateBizUserFeedback(BizUserFeedback bizUserFeedback)
    {
        return bizUserFeedbackMapper.updateBizUserFeedback(bizUserFeedback);
    }

    /**
     * 批量删除用户反馈
     * 
     * @param ids 需要删除的用户反馈主键
     * @return 结果
     */
    @Override
    public int deleteBizUserFeedbackByIds(Long[] ids)
    {
        return bizUserFeedbackMapper.deleteBizUserFeedbackByIds(ids);
    }

    /**
     * 删除用户反馈信息
     * 
     * @param id 用户反馈主键
     * @return 结果
     */
    @Override
    public int deleteBizUserFeedbackById(Long id)
    {
        return bizUserFeedbackMapper.deleteBizUserFeedbackById(id);
    }
}
