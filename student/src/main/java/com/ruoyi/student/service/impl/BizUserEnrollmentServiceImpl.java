package com.ruoyi.student.service.impl;

import java.util.List;

import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.BizUserEnrollmentMapper;
import com.ruoyi.student.domain.BizUserEnrollment;
import com.ruoyi.student.service.IBizUserEnrollmentService;

/**
 * 用户活动报名记录Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Service
public class BizUserEnrollmentServiceImpl implements IBizUserEnrollmentService
{
    @Autowired
    private BizUserEnrollmentMapper bizUserEnrollmentMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 查询用户活动报名记录
     *
     * @param id 用户活动报名记录主键
     * @return 用户活动报名记录
     */
    @Override
    public BizUserEnrollment selectBizUserEnrollmentById(Long id)
    {
        return bizUserEnrollmentMapper.selectBizUserEnrollmentById(id);
    }

    /**
     * 查询用户活动报名记录列表
     *
     * @param bizUserEnrollment 用户活动报名记录
     * @return 用户活动报名记录
     */
    @Override
    public List<BizUserEnrollment> selectBizUserEnrollmentList(BizUserEnrollment bizUserEnrollment)
    {
        return bizUserEnrollmentMapper.selectBizUserEnrollmentList(bizUserEnrollment);
    }

    /**
     * 新增用户活动报名记录
     *
     * @param bizUserEnrollment 用户活动报名记录
     * @return 结果
     */
    @Override
    public int insertBizUserEnrollment(BizUserEnrollment bizUserEnrollment)
    {
        return bizUserEnrollmentMapper.insertBizUserEnrollment(bizUserEnrollment);
    }

    /**
     * 修改用户活动报名记录
     *
     * @param bizUserEnrollment 用户活动报名记录
     * @return 结果
     */
    @Override
    public int updateBizUserEnrollment(BizUserEnrollment bizUserEnrollment)
    {
        return bizUserEnrollmentMapper.updateBizUserEnrollment(bizUserEnrollment);
    }

    /**
     * 批量删除用户活动报名记录
     *
     * @param ids 需要删除的用户活动报名记录主键
     * @return 结果
     */
    @Override
    public int deleteBizUserEnrollmentByIds(Long[] ids)
    {
        return bizUserEnrollmentMapper.deleteBizUserEnrollmentByIds(ids);
    }

    /**
     * 删除用户活动报名记录信息
     *
     * @param id 用户活动报名记录主键
     * @return 结果
     */
    @Override
    public int deleteBizUserEnrollmentById(Long id)
    {
        return bizUserEnrollmentMapper.deleteBizUserEnrollmentById(id);
    }
}
