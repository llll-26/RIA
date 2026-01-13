package com.ruoyi.student.controller.activity;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.student.domain.BizActivity;
import com.ruoyi.student.service.IBizActivityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 志愿活动/讲座Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@RestController
@RequestMapping("/student/activity")
public class BizActivityController extends BaseController
{
    @Autowired
    private IBizActivityService bizActivityService;

    /**
     * 查询志愿活动/讲座列表
     */
    @PreAuthorize("@ss.hasPermi('student:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizActivity bizActivity)
    {
        startPage();
        List<BizActivity> list = bizActivityService.selectBizActivityList(bizActivity);
        return getDataTable(list);
    }

    /**
     * 导出志愿活动/讲座列表
     */
    @PreAuthorize("@ss.hasPermi('student:activity:export')")
    @Log(title = "志愿活动/讲座", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizActivity bizActivity)
    {
        List<BizActivity> list = bizActivityService.selectBizActivityList(bizActivity);
        ExcelUtil<BizActivity> util = new ExcelUtil<BizActivity>(BizActivity.class);
        util.exportExcel(response, list, "志愿活动/讲座数据");
    }

    /**
     * 获取志愿活动/讲座详细信息
     */

    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bizActivityService.selectBizActivityById(id));

    }

    /**
     * 新增志愿活动/讲座
     */
    @PreAuthorize("@ss.hasPermi('student:activity:add')")
    @Log(title = "志愿活动/讲座", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizActivity bizActivity)
    {
        return toAjax(bizActivityService.insertBizActivity(bizActivity));
    }

    /**
     * 修改志愿活动/讲座
     */
    @PreAuthorize("@ss.hasPermi('student:activity:edit')")
    @Log(title = "志愿活动/讲座", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizActivity bizActivity)
    {
        return toAjax(bizActivityService.updateBizActivity(bizActivity));
    }

    /**
     * 删除志愿活动/讲座
     */
    @PreAuthorize("@ss.hasPermi('student:activity:remove')")
    @Log(title = "志愿活动/讲座", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizActivityService.deleteBizActivityByIds(ids));
    }
    /**
     * 下架活动（仅隐藏，保留数据）
     */
    @PutMapping("/hide/{ids}")
    public AjaxResult hide(@PathVariable Long[] ids) {
        return toAjax(bizActivityService.hideActivities(ids));
    }

}
