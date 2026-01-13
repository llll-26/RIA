package com.ruoyi.student.controller.skill;

import java.util.Arrays;
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
import com.ruoyi.student.domain.BizSkillService;
import com.ruoyi.student.service.IBizSkillServiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 技能服务Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@RestController
@RequestMapping("/student/service")
public class BizSkillServiceController extends BaseController
{
    @Autowired
    private IBizSkillServiceService bizSkillServiceService;

    /**
     * 查询技能服务列表
     */
    @PreAuthorize("@ss.hasPermi('student:service:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizSkillService bizSkillService)
    {
        startPage();
        List<BizSkillService> list = bizSkillServiceService.selectBizSkillServiceList(bizSkillService);
        return getDataTable(list);
    }

    /**
     * 导出技能服务列表
     */
    @PreAuthorize("@ss.hasPermi('student:service:export')")
    @Log(title = "技能服务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizSkillService bizSkillService)
    {
        List<BizSkillService> list = bizSkillServiceService.selectBizSkillServiceList(bizSkillService);
        ExcelUtil<BizSkillService> util = new ExcelUtil<BizSkillService>(BizSkillService.class);
        util.exportExcel(response, list, "技能服务数据");
    }

    /**
     * 获取技能服务详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:service:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bizSkillServiceService.selectBizSkillServiceById(id));
    }

    /**
     * 新增技能服务
     */
    @PreAuthorize("@ss.hasPermi('student:service:add')")
    @Log(title = "技能服务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizSkillService bizSkillService)
    {
        return toAjax(bizSkillServiceService.insertBizSkillService(bizSkillService));
    }

    /**
     * 删除技能服务
     */
    @PreAuthorize("@ss.hasPermi('student:service:remove')")
    @Log(title = "技能服务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizSkillServiceService.deleteBizSkillServiceByIds(ids));
    }

    /**
     * 审核通过技能服务
     */
    /**
     * 审核通过技能服务
     */
    @PreAuthorize("@ss.hasPermi('student:service:audit')")
    @Log(title = "技能服务", businessType = BusinessType.UPDATE)
    @PostMapping("/audit/pass/{id}")
    public AjaxResult pass(@PathVariable Long id) {
        bizSkillServiceService.updateStatus(id, 1); // 1 = 通过
        return AjaxResult.success();
    }

    /**
     * 审核拒绝技能服务
     */
    @PreAuthorize("@ss.hasPermi('student:service:audit')")
    @Log(title = "技能服务", businessType = BusinessType.UPDATE)
    @PostMapping("/audit/reject/{id}")
    public AjaxResult reject(@PathVariable Long id) {
        bizSkillServiceService.updateStatus(id, 2); // 2 = 拒绝
        return AjaxResult.success();
    }

}
