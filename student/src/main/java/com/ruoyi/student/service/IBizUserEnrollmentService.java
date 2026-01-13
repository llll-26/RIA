package com.ruoyi.student.service;

import java.util.List;
import com.ruoyi.student.domain.BizUserEnrollment;

/**
 * 用户活动报名记录Service接口
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
public interface IBizUserEnrollmentService 
{
    /**
     * 查询用户活动报名记录
     * 
     * @param id 用户活动报名记录主键
     * @return 用户活动报名记录
     */
    public BizUserEnrollment selectBizUserEnrollmentById(Long id);

    /**
     * 查询用户活动报名记录列表
     * 
     * @param bizUserEnrollment 用户活动报名记录
     * @return 用户活动报名记录集合
     */
    public List<BizUserEnrollment> selectBizUserEnrollmentList(BizUserEnrollment bizUserEnrollment);

    /**
     * 新增用户活动报名记录
     * 
     * @param bizUserEnrollment 用户活动报名记录
     * @return 结果
     */
    public int insertBizUserEnrollment(BizUserEnrollment bizUserEnrollment);

    /**
     * 修改用户活动报名记录
     * 
     * @param bizUserEnrollment 用户活动报名记录
     * @return 结果
     */
    public int updateBizUserEnrollment(BizUserEnrollment bizUserEnrollment);

    /**
     * 批量删除用户活动报名记录
     * 
     * @param ids 需要删除的用户活动报名记录主键集合
     * @return 结果
     */
    public int deleteBizUserEnrollmentByIds(Long[] ids);

    /**
     * 删除用户活动报名记录信息
     * 
     * @param id 用户活动报名记录主键
     * @return 结果
     */
    public int deleteBizUserEnrollmentById(Long id);
}
