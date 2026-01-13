package com.ruoyi.student.controller.reward;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.student.domain.BizRewardItem;
import com.ruoyi.student.service.IBizRewardItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 兑换奖品项Controller
 *
 * @author ruoyi
 * @date 2025-12-27
 */
@RestController
@RequestMapping("/student/item")
public class BizRewardItemController extends BaseController
{
    @Autowired
    private IBizRewardItemService bizRewardItemService;

    /**
     * 查询兑换奖品项列表
     */
    @PreAuthorize("@ss.hasPermi('student:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizRewardItem bizRewardItem)
    {
        startPage();
        List<BizRewardItem> list = bizRewardItemService.selectBizRewardItemList(bizRewardItem);
        return getDataTable(list);
    }

    /**
     * 导出兑换奖品项列表
     */
    @PreAuthorize("@ss.hasPermi('student:item:export')")
    @Log(title = "兑换奖品项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizRewardItem bizRewardItem)
    {
        List<BizRewardItem> list = bizRewardItemService.selectBizRewardItemList(bizRewardItem);
        ExcelUtil<BizRewardItem> util = new ExcelUtil<BizRewardItem>(BizRewardItem.class);
        util.exportExcel(response, list, "兑换奖品项数据");
    }

    /**
     * 获取兑换奖品项详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bizRewardItemService.selectBizRewardItemById(id));
    }

    /**
     * 新增兑换奖品项
     */
    @PreAuthorize("@ss.hasPermi('student:item:add')")
    @Log(title = "兑换奖品项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizRewardItem bizRewardItem)
    {
        return toAjax(bizRewardItemService.insertBizRewardItem(bizRewardItem));
    }

    /**
     * 修改兑换奖品项
     */
    @PreAuthorize("@ss.hasPermi('student:item:edit')")
    @Log(title = "兑换奖品项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizRewardItem bizRewardItem)
    {
        return toAjax(bizRewardItemService.updateBizRewardItem(bizRewardItem));
    }

    /**
     * 删除兑换奖品项
     */
    @PreAuthorize("@ss.hasPermi('student:item:remove')")
    @Log(title = "兑换奖品项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizRewardItemService.deleteBizRewardItemByIds(ids));
    }
    /**
     * 下架奖品（仅隐藏，保留数据）
     */
    @PreAuthorize("@ss.hasPermi('student:item:edit')")
    @Log(title = "兑换奖品项", businessType = BusinessType.UPDATE)
    @PutMapping("/hide/{ids}")
    public AjaxResult hide(@PathVariable Long[] ids) {
        return toAjax(bizRewardItemService.hideRewardItems(ids));
    }

}
