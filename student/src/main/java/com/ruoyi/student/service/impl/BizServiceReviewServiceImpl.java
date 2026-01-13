package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.BizServiceReviewMapper;
import com.ruoyi.student.domain.BizServiceReview;
import com.ruoyi.student.service.IBizServiceReviewService;

/**
 * 服务评价Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
@Service
public class BizServiceReviewServiceImpl implements IBizServiceReviewService 
{
    @Autowired
    private BizServiceReviewMapper bizServiceReviewMapper;

    /**
     * 查询服务评价
     * 
     * @param id 服务评价主键
     * @return 服务评价
     */
    @Override
    public BizServiceReview selectBizServiceReviewById(Long id)
    {
        return bizServiceReviewMapper.selectBizServiceReviewById(id);
    }

    /**
     * 查询服务评价列表
     * 
     * @param bizServiceReview 服务评价
     * @return 服务评价
     */
    @Override
    public List<BizServiceReview> selectBizServiceReviewList(BizServiceReview bizServiceReview)
    {
        return bizServiceReviewMapper.selectBizServiceReviewList(bizServiceReview);
    }

    /**
     * 新增服务评价
     * 
     * @param bizServiceReview 服务评价
     * @return 结果
     */
    @Override
    public int insertBizServiceReview(BizServiceReview bizServiceReview)
    {
        return bizServiceReviewMapper.insertBizServiceReview(bizServiceReview);
    }

    /**
     * 修改服务评价
     * 
     * @param bizServiceReview 服务评价
     * @return 结果
     */
    @Override
    public int updateBizServiceReview(BizServiceReview bizServiceReview)
    {
        return bizServiceReviewMapper.updateBizServiceReview(bizServiceReview);
    }

    /**
     * 批量删除服务评价
     * 
     * @param ids 需要删除的服务评价主键
     * @return 结果
     */
    @Override
    public int deleteBizServiceReviewByIds(Long[] ids)
    {
        return bizServiceReviewMapper.deleteBizServiceReviewByIds(ids);
    }

    /**
     * 删除服务评价信息
     * 
     * @param id 服务评价主键
     * @return 结果
     */
    @Override
    public int deleteBizServiceReviewById(Long id)
    {
        return bizServiceReviewMapper.deleteBizServiceReviewById(id);
    }
}
