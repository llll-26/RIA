package com.ruoyi.student.controller.review;

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
import com.ruoyi.student.domain.BizServiceReview;
import com.ruoyi.student.service.IBizServiceReviewService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务评价Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@RestController
@RequestMapping("/student/review")
public class BizServiceReviewController extends BaseController
{
    @Autowired
    private IBizServiceReviewService bizServiceReviewService;

    /**
     * 查询服务评价列表
     */
    @PreAuthorize("@ss.hasPermi('student:review:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizServiceReview bizServiceReview)
    {
        startPage();
        List<BizServiceReview> list = bizServiceReviewService.selectBizServiceReviewList(bizServiceReview);
        return getDataTable(list);
    }

    /**
     * 导出服务评价列表
     */
    @PreAuthorize("@ss.hasPermi('student:review:export')")
    @Log(title = "服务评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizServiceReview bizServiceReview)
    {
        List<BizServiceReview> list = bizServiceReviewService.selectBizServiceReviewList(bizServiceReview);
        ExcelUtil<BizServiceReview> util = new ExcelUtil<BizServiceReview>(BizServiceReview.class);
        util.exportExcel(response, list, "服务评价数据");
    }

    /**
     * 获取服务评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:review:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bizServiceReviewService.selectBizServiceReviewById(id));
    }

    /**
     * 新增服务评价
     */
    @PreAuthorize("@ss.hasPermi('student:review:add')")
    @Log(title = "服务评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizServiceReview bizServiceReview)
    {
        return toAjax(bizServiceReviewService.insertBizServiceReview(bizServiceReview));
    }

    /**
     * 修改服务评价
     */
    @PreAuthorize("@ss.hasPermi('student:review:edit')")
    @Log(title = "服务评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizServiceReview bizServiceReview)
    {
        return toAjax(bizServiceReviewService.updateBizServiceReview(bizServiceReview));
    }

    /**
     * 删除服务评价
     */
    @PreAuthorize("@ss.hasPermi('student:review:remove')")
    @Log(title = "服务评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizServiceReviewService.deleteBizServiceReviewByIds(ids));
    }
}
