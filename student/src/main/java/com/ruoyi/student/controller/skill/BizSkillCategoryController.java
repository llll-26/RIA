package com.ruoyi.student.controller.skill;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.student.domain.BizSkillCategory;
import com.ruoyi.student.service.IBizSkillCategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 技能标准分类Controller
 *
 * @author ruoyi
 * @date 2025-12-12
 */
@RestController
@RequestMapping("/student/category")
public class BizSkillCategoryController extends BaseController
{
    @Autowired
    private IBizSkillCategoryService bizSkillCategoryService;

    /**
     * 查询技能标准分类列表
     */
    @PreAuthorize("@ss.hasPermi('student:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizSkillCategory bizSkillCategory)
    {
        startPage();
        List<BizSkillCategory> list = bizSkillCategoryService.selectBizSkillCategoryList(bizSkillCategory);
        return getDataTable(list);
    }

    /**
     * 导出技能标准分类列表
     */
    @PreAuthorize("@ss.hasPermi('student:category:export')")
    @Log(title = "技能标准分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizSkillCategory bizSkillCategory)
    {
        List<BizSkillCategory> list = bizSkillCategoryService.selectBizSkillCategoryList(bizSkillCategory);
        ExcelUtil<BizSkillCategory> util = new ExcelUtil<BizSkillCategory>(BizSkillCategory.class);
        util.exportExcel(response, list, "技能标准分类数据");
    }

    /**
     * 获取技能标准分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bizSkillCategoryService.selectBizSkillCategoryById(id));
    }

    /**
     * 新增技能标准分类
     */
    @PreAuthorize("@ss.hasPermi('student:category:add')")
    @Log(title = "技能标准分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizSkillCategory bizSkillCategory)
    {
        return toAjax(bizSkillCategoryService.insertBizSkillCategory(bizSkillCategory));
    }

    /**
     * 修改技能标准分类
     */
    @PreAuthorize("@ss.hasPermi('student:category:edit')")
    @Log(title = "技能标准分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizSkillCategory bizSkillCategory)
    {
        return toAjax(bizSkillCategoryService.updateBizSkillCategory(bizSkillCategory));
    }

    /**
     * 删除技能标准分类
     */
    @PreAuthorize("@ss.hasPermi('student:category:remove')")
    @Log(title = "技能标准分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizSkillCategoryService.deleteBizSkillCategoryByIds(ids));
    }
}
