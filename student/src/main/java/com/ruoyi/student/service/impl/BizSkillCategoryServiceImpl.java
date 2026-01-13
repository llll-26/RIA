package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.BizSkillCategoryMapper;
import com.ruoyi.student.domain.BizSkillCategory;
import com.ruoyi.student.service.IBizSkillCategoryService;

/**
 * 技能标准分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Service
public class BizSkillCategoryServiceImpl implements IBizSkillCategoryService 
{
    @Autowired
    private BizSkillCategoryMapper bizSkillCategoryMapper;

    /**
     * 查询技能标准分类
     * 
     * @param id 技能标准分类主键
     * @return 技能标准分类
     */
    @Override
    public BizSkillCategory selectBizSkillCategoryById(Long id)
    {
        return bizSkillCategoryMapper.selectBizSkillCategoryById(id);
    }

    /**
     * 查询技能标准分类列表
     * 
     * @param bizSkillCategory 技能标准分类
     * @return 技能标准分类
     */
    @Override
    public List<BizSkillCategory> selectBizSkillCategoryList(BizSkillCategory bizSkillCategory)
    {
        return bizSkillCategoryMapper.selectBizSkillCategoryList(bizSkillCategory);
    }

    /**
     * 新增技能标准分类
     * 
     * @param bizSkillCategory 技能标准分类
     * @return 结果
     */
    @Override
    public int insertBizSkillCategory(BizSkillCategory bizSkillCategory)
    {
        return bizSkillCategoryMapper.insertBizSkillCategory(bizSkillCategory);
    }

    /**
     * 修改技能标准分类
     * 
     * @param bizSkillCategory 技能标准分类
     * @return 结果
     */
    @Override
    public int updateBizSkillCategory(BizSkillCategory bizSkillCategory)
    {
        return bizSkillCategoryMapper.updateBizSkillCategory(bizSkillCategory);
    }

    /**
     * 批量删除技能标准分类
     * 
     * @param ids 需要删除的技能标准分类主键
     * @return 结果
     */
    @Override
    public int deleteBizSkillCategoryByIds(Long[] ids)
    {
        return bizSkillCategoryMapper.deleteBizSkillCategoryByIds(ids);
    }

    /**
     * 删除技能标准分类信息
     * 
     * @param id 技能标准分类主键
     * @return 结果
     */
    @Override
    public int deleteBizSkillCategoryById(Long id)
    {
        return bizSkillCategoryMapper.deleteBizSkillCategoryById(id);
    }
}
