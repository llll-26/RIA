package com.ruoyi.web.controller.system;

import java.util.Map;
import java.util.Optional;

import com.ruoyi.common.core.domain.entity.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysUserService;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile()
    {
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
        ajax.put("postGroup", userService.selectUserPostGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 修改用户
     */
    @PutMapping
    public AjaxResult updateProfile(@RequestBody SysUser user) {
        LoginUser loginUser = getLoginUser();
        SysUser currentUser = loginUser.getUser();

        // 先保存原始值（用于比较）
        String oldPhone = currentUser.getPhonenumber();
        String oldEmail = currentUser.getEmail();

        // 更新字段
        currentUser.setNickName(user.getNickName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhonenumber(user.getPhonenumber());
        currentUser.setSex(user.getSex());

        // 只有当手机号不为空 且 发生变化时，才校验唯一性
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && !user.getPhonenumber().equals(oldPhone)) {
            if (!userService.checkPhoneUnique(currentUser)) {
                return error("修改用户'" + loginUser.getUsername() + "'失败，手机号码已存在");
            }
        }

        //邮箱同理
        if (StringUtils.isNotEmpty(user.getEmail())
                && !user.getEmail().equals(oldEmail)) {
            if (!userService.checkEmailUnique(currentUser)) {
                return error("修改用户'" + loginUser.getUsername() + "'失败，邮箱账号已存在");
            }
        }

        if (userService.updateUserProfile(currentUser) > 0) {
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(@RequestBody Map<String, String> params)
    {
        String oldPassword = params.get("oldPassword");
        String newPassword = params.get("newPassword");
        LoginUser loginUser = getLoginUser();
        Long userId = loginUser.getUserId();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password))
        {
            return error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password))
        {
            return error("新密码不能与旧密码相同");
        }
        newPassword = SecurityUtils.encryptPassword(newPassword);
        if (userService.resetUserPwd(userId, newPassword) > 0)
        {
            // 更新缓存用户密码&密码最后更新时间
            loginUser.getUser().setPwdUpdateDate(DateUtils.getNowDate());
            loginUser.getUser().setPassword(newPassword);
            tokenService.setLoginUser(loginUser);
            return success();
        }
        return error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws Exception
    {
        if (!file.isEmpty())
        {
            LoginUser loginUser = getLoginUser();
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file, MimeTypeUtils.IMAGE_EXTENSION, true);
            if (userService.updateUserAvatar(loginUser.getUserId(), avatar))
            {
                String oldAvatar = loginUser.getUser().getAvatar();
                if (StringUtils.isNotEmpty(oldAvatar))
                {
                    FileUtils.deleteFile(RuoYiConfig.getProfile() + FileUtils.stripPrefix(oldAvatar));
                }
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(avatar);
                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return error("上传图片异常，请联系管理员");
    }
    /**
     * 获取用户收到的评分统计
     */
    @GetMapping("/received-ratings")
    public AjaxResult getReceivedRatings() {
        LoginUser loginUser = getLoginUser();
        Long userId = loginUser.getUserId();

        Map<String, Object> ratingStats = userService.getReceivedRatings(userId);
        return AjaxResult.success(ratingStats);
    }
    /**
     * 获取其他用户的公开信息（非静态！）
     */
    @GetMapping("/public/{userId}")
    public AjaxResult getUserPublicInfo(@PathVariable Long userId) {
        if (userId == null || userId <= 0) {
            return AjaxResult.error("无效的用户ID");
        }

        SysUser user = userService.selectUserById(userId);
        if (user == null) {
            return AjaxResult.error("用户不存在");
        }

        String college = Optional.ofNullable(user.getDept())
                .map(SysDept::getDeptName)
                .orElse("");

        return AjaxResult.success()
                .put("userId", user.getUserId())
                .put("userName", user.getUserName())
                .put("nickName", user.getNickName())
                .put("sex", user.getSex())
                .put("college", college)
                .put("major", user.getMajor())
                .put("avatar", user.getAvatar());
    }

    /**
     * 获取其他用户的评分统计
     */
    @GetMapping("/public/rating/received/{userId}")
    public AjaxResult getReceivedRatingsByUserId(@PathVariable Long userId) {
        if (userId == null || userId <= 0) {
            return AjaxResult.error("无效的用户ID");
        }

        Map<String, Object> stats = userService.getReceivedRatings(userId);
        if (stats == null) {
            stats = Map.of("averageScore", 0.0, "totalReviews", 0);
        }
        return AjaxResult.success(stats);
    }
}
