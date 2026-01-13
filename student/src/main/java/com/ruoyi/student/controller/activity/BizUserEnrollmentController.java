package com.ruoyi.student.controller.activity;

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
import com.ruoyi.student.domain.BizUserEnrollment;
import com.ruoyi.student.service.IBizUserEnrollmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户活动报名记录Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@RestController
@RequestMapping("/student/enrollment")
public class BizUserEnrollmentController extends BaseController
{
    @Autowired
    private IBizUserEnrollmentService bizUserEnrollmentService;

    /**
     * 查询用户活动报名记录列表
     */
    @PreAuthorize("@ss.hasPermi('student:enrollment:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizUserEnrollment bizUserEnrollment)
    {
        startPage();
        List<BizUserEnrollment> list = bizUserEnrollmentService.selectBizUserEnrollmentList(bizUserEnrollment);
        return getDataTable(list);
    }

    /**
     * 导出用户活动报名记录列表
     */
    @PreAuthorize("@ss.hasPermi('student:enrollment:export')")
    @Log(title = "用户活动报名记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizUserEnrollment bizUserEnrollment)
    {
        List<BizUserEnrollment> list = bizUserEnrollmentService.selectBizUserEnrollmentList(bizUserEnrollment);
        ExcelUtil<BizUserEnrollment> util = new ExcelUtil<BizUserEnrollment>(BizUserEnrollment.class);
        util.exportExcel(response, list, "用户活动报名记录数据");
    }

    /**
     * 获取用户活动报名记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:enrollment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bizUserEnrollmentService.selectBizUserEnrollmentById(id));
    }

    /**
     * 新增用户活动报名记录
     */
    @PreAuthorize("@ss.hasPermi('student:enrollment:add')")
    @Log(title = "用户活动报名记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizUserEnrollment bizUserEnrollment)
    {
        return toAjax(bizUserEnrollmentService.insertBizUserEnrollment(bizUserEnrollment));
    }

    /**
     * 修改用户活动报名记录
     */
    @PreAuthorize("@ss.hasPermi('student:enrollment:edit')")
    @Log(title = "用户活动报名记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizUserEnrollment bizUserEnrollment)
    {
        return toAjax(bizUserEnrollmentService.updateBizUserEnrollment(bizUserEnrollment));
    }

    /**
     * 删除用户活动报名记录
     */
    @PreAuthorize("@ss.hasPermi('student:enrollment:remove')")
    @Log(title = "用户活动报名记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizUserEnrollmentService.deleteBizUserEnrollmentByIds(ids));
    }
}
