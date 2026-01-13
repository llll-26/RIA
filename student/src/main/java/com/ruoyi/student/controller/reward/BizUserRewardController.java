package com.ruoyi.student.controller.reward;

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
import com.ruoyi.student.domain.BizUserReward;
import com.ruoyi.student.service.IBizUserRewardService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户兑换记录Controller
 *
 * @author ruoyi
 * @date 2025-12-27
 */
@RestController
@RequestMapping("/student/reward")
public class BizUserRewardController extends BaseController
{
    @Autowired
    private IBizUserRewardService bizUserRewardService;

    /**
     * 查询用户兑换记录列表
     */
    @PreAuthorize("@ss.hasPermi('student:reward:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizUserReward bizUserReward)
    {
        startPage();
        List<BizUserReward> list = bizUserRewardService.selectBizUserRewardList(bizUserReward);
        return getDataTable(list);
    }

    /**
     * 导出用户兑换记录列表
     */
    @PreAuthorize("@ss.hasPermi('student:reward:export')")
    @Log(title = "用户兑换记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizUserReward bizUserReward)
    {
        List<BizUserReward> list = bizUserRewardService.selectBizUserRewardList(bizUserReward);
        ExcelUtil<BizUserReward> util = new ExcelUtil<BizUserReward>(BizUserReward.class);
        util.exportExcel(response, list, "用户兑换记录数据");
    }

    /**
     * 获取用户兑换记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:reward:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bizUserRewardService.selectBizUserRewardById(id));
    }

    /**
     * 新增用户兑换记录
     */
    @PreAuthorize("@ss.hasPermi('student:reward:add')")
    @Log(title = "用户兑换记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizUserReward bizUserReward)
    {
        return toAjax(bizUserRewardService.insertBizUserReward(bizUserReward));
    }

    /**
     * 修改用户兑换记录
     */
    @PreAuthorize("@ss.hasPermi('student:reward:edit')")
    @Log(title = "用户兑换记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizUserReward bizUserReward)
    {
        return toAjax(bizUserRewardService.updateBizUserReward(bizUserReward));
    }

    /**
     * 删除用户兑换记录
     */
    @PreAuthorize("@ss.hasPermi('student:reward:remove')")
    @Log(title = "用户兑换记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizUserRewardService.deleteBizUserRewardByIds(ids));
    }
}
