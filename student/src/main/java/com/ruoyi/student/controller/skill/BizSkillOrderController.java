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
import com.ruoyi.student.domain.BizSkillOrder;
import com.ruoyi.student.service.IBizSkillOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 技能订单Controller
 *
 * @author ruoyi
 * @date 2025-12-27
 */
@RestController
@RequestMapping("/student/order")
public class BizSkillOrderController extends BaseController
{
    @Autowired
    private IBizSkillOrderService bizSkillOrderService;

    /**
     * 查询技能订单列表
     */
    @PreAuthorize("@ss.hasPermi('student:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizSkillOrder bizSkillOrder)
    {
        startPage();
        List<BizSkillOrder> list = bizSkillOrderService.selectBizSkillOrderList(bizSkillOrder);
        return getDataTable(list);
    }

    /**
     * 导出技能订单列表
     */
    @PreAuthorize("@ss.hasPermi('student:order:export')")
    @Log(title = "技能订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizSkillOrder bizSkillOrder)
    {
        List<BizSkillOrder> list = bizSkillOrderService.selectBizSkillOrderList(bizSkillOrder);
        ExcelUtil<BizSkillOrder> util = new ExcelUtil<BizSkillOrder>(BizSkillOrder.class);
        util.exportExcel(response, list, "技能订单数据");
    }

    /**
     * 获取技能订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bizSkillOrderService.selectBizSkillOrderById(id));
    }

    /**
     * 新增技能订单
     */
    @PreAuthorize("@ss.hasPermi('student:order:add')")
    @Log(title = "技能订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizSkillOrder bizSkillOrder)
    {
        return toAjax(bizSkillOrderService.insertBizSkillOrder(bizSkillOrder));
    }

    /**
     * 修改技能订单
     */
    @PreAuthorize("@ss.hasPermi('student:order:edit')")
    @Log(title = "技能订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizSkillOrder bizSkillOrder)
    {
        return toAjax(bizSkillOrderService.updateBizSkillOrder(bizSkillOrder));
    }

    /**
     * 删除技能订单
     */
    @PreAuthorize("@ss.hasPermi('student:order:remove')")
    @Log(title = "技能订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizSkillOrderService.deleteBizSkillOrderByIds(ids));
    }
}
