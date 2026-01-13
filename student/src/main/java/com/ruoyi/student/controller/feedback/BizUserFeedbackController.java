package com.ruoyi.student.controller.feedback;

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
import com.ruoyi.student.domain.BizUserFeedback;
import com.ruoyi.student.service.IBizUserFeedbackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户反馈Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@RestController
@RequestMapping("/student/feedback")
public class BizUserFeedbackController extends BaseController
{
    @Autowired
    private IBizUserFeedbackService bizUserFeedbackService;

    /**
     * 查询用户反馈列表
     */
    @PreAuthorize("@ss.hasPermi('student:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizUserFeedback bizUserFeedback)
    {
        startPage();
        List<BizUserFeedback> list = bizUserFeedbackService.selectBizUserFeedbackList(bizUserFeedback);
        return getDataTable(list);
    }

    /**
     * 导出用户反馈列表
     */
    @PreAuthorize("@ss.hasPermi('student:feedback:export')")
    @Log(title = "用户反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizUserFeedback bizUserFeedback)
    {
        List<BizUserFeedback> list = bizUserFeedbackService.selectBizUserFeedbackList(bizUserFeedback);
        ExcelUtil<BizUserFeedback> util = new ExcelUtil<BizUserFeedback>(BizUserFeedback.class);
        util.exportExcel(response, list, "用户反馈数据");
    }

    /**
     * 获取用户反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:feedback:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bizUserFeedbackService.selectBizUserFeedbackById(id));
    }

    /**
     * 新增用户反馈
     */
    @PreAuthorize("@ss.hasPermi('student:feedback:add')")
    @Log(title = "用户反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizUserFeedback bizUserFeedback)
    {
        return toAjax(bizUserFeedbackService.insertBizUserFeedback(bizUserFeedback));
    }

    /**
     * 修改用户反馈
     */
    @PreAuthorize("@ss.hasPermi('student:feedback:edit')")
    @Log(title = "用户反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizUserFeedback bizUserFeedback)
    {
        return toAjax(bizUserFeedbackService.updateBizUserFeedback(bizUserFeedback));
    }

    /**
     * 删除用户反馈
     */
    @PreAuthorize("@ss.hasPermi('student:feedback:remove')")
    @Log(title = "用户反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizUserFeedbackService.deleteBizUserFeedbackByIds(ids));
    }
}
