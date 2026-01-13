package com.ruoyi.student.controller.notification;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.model.LoginUser;
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
import com.ruoyi.student.domain.BizUserNotification;
import com.ruoyi.student.service.IBizUserNotificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 用户通知Controller
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@RestController
@RequestMapping("/student/notification")
public class BizUserNotificationController extends BaseController
{
    @Autowired
    private IBizUserNotificationService bizUserNotificationService;

    /**
     * 查询用户通知列表
     */
    @PreAuthorize("@ss.hasPermi('student:notification:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizUserNotification bizUserNotification)
    {
        startPage();
        List<BizUserNotification> list = bizUserNotificationService.selectBizUserNotificationList(bizUserNotification);
        return getDataTable(list);
    }

    /**
     * 导出用户通知列表
     */
    @PreAuthorize("@ss.hasPermi('student:notification:export')")
    @Log(title = "用户通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizUserNotification bizUserNotification)
    {
        List<BizUserNotification> list = bizUserNotificationService.selectBizUserNotificationList(bizUserNotification);
        ExcelUtil<BizUserNotification> util = new ExcelUtil<BizUserNotification>(BizUserNotification.class);
        util.exportExcel(response, list, "用户通知数据");
    }

    /**
     * 获取用户通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:notification:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bizUserNotificationService.selectBizUserNotificationById(id));
    }

    /**
     * 新增用户通知
     */
    @PreAuthorize("@ss.hasPermi('student:notification:add')")
    @Log(title = "用户通知", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BizUserNotification bizUserNotification)
    {
        return toAjax(bizUserNotificationService.insertBizUserNotification(bizUserNotification));
    }

    /**
     * 修改用户通知
     */
    @PreAuthorize("@ss.hasPermi('student:notification:edit')")
    @Log(title = "用户通知", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BizUserNotification bizUserNotification)
    {
        return toAjax(bizUserNotificationService.updateBizUserNotification(bizUserNotification));
    }

    /**
     * 删除用户通知
     */
    @PreAuthorize("@ss.hasPermi('student:notification:remove')")
    @Log(title = "用户通知", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bizUserNotificationService.deleteBizUserNotificationByIds(ids));
    }
    /**
     * 获取当前用户的未读通知数量
     */
    @GetMapping("/unread-count")
    public AjaxResult getUnreadNotificationCount() {
        try {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            if (loginUser == null || loginUser.getUser() == null) {
                return success(0); // 未登录视为 0 条未读
            }
            Long userId = loginUser.getUser().getUserId();
            Integer count = bizUserNotificationService.countUnreadByUserId(userId);
            return success(count == null ? 0 : count);
        } catch (Exception e) {
            log.warn("获取未读通知数失败", e);
            return success(0);
        }
    }

}
