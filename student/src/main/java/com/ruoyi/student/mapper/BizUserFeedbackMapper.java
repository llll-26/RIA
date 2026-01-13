package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.BizUserFeedback;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户反馈Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Mapper
public interface BizUserFeedbackMapper
{
    /**
     * 查询用户反馈
     *
     * @param id 用户反馈主键
     * @return 用户反馈
     */
    public BizUserFeedback selectBizUserFeedbackById(Long id);

    /**
     * 查询用户反馈列表
     *
     * @param bizUserFeedback 用户反馈
     * @return 用户反馈集合
     */
    public List<BizUserFeedback> selectBizUserFeedbackList(BizUserFeedback bizUserFeedback);

    /**
     * 新增用户反馈
     *
     * @param bizUserFeedback 用户反馈
     * @return 结果
     */
    public int insertBizUserFeedback(BizUserFeedback bizUserFeedback);

    /**
     * 修改用户反馈
     *
     * @param bizUserFeedback 用户反馈
     * @return 结果
     */
    public int updateBizUserFeedback(BizUserFeedback bizUserFeedback);

    /**
     * 删除用户反馈
     *
     * @param id 用户反馈主键
     * @return 结果
     */
    public int deleteBizUserFeedbackById(Long id);

    /**
     * 批量删除用户反馈
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizUserFeedbackByIds(Long[] ids);
}
