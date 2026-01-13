package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.BizServiceReview;
import org.apache.ibatis.annotations.Mapper;

/**
 * 服务评价Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Mapper
public interface BizServiceReviewMapper
{
    /**
     * 查询服务评价
     *
     * @param id 服务评价主键
     * @return 服务评价
     */
    public BizServiceReview selectBizServiceReviewById(Long id);

    /**
     * 查询服务评价列表
     *
     * @param bizServiceReview 服务评价
     * @return 服务评价集合
     */
    public List<BizServiceReview> selectBizServiceReviewList(BizServiceReview bizServiceReview);

    /**
     * 新增服务评价
     *
     * @param bizServiceReview 服务评价
     * @return 结果
     */
    public int insertBizServiceReview(BizServiceReview bizServiceReview);

    /**
     * 修改服务评价
     *
     * @param bizServiceReview 服务评价
     * @return 结果
     */
    public int updateBizServiceReview(BizServiceReview bizServiceReview);

    /**
     * 删除服务评价
     *
     * @param id 服务评价主键
     * @return 结果
     */
    public int deleteBizServiceReviewById(Long id);

    /**
     * 批量删除服务评价
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizServiceReviewByIds(Long[] ids);
}
