package com.ruoyi.framework.web.service;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.domain.SysStudentWhitelist;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysStudentWhitelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.RegisterBody;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;

import java.util.List;

/**
 * 注册校验方法
 *
 * @author ruoyi
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private List<IUserRegisterExtension> registerExtensions;
    @Autowired
    private ISysStudentWhitelistService whitelistService;
    /**
     * 注册
     */
    public String register(RegisterBody registerBody) {
        String msg = "";
        String username = registerBody.getUsername(); // 学号
        String password = registerBody.getPassword();

        // 验证码校验
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled) {
            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
        }

        // 学号必须是数字
        if (!StringUtils.isNumeric(username)) {
            return "学号必须为纯数字";
        }

        //校验学号是否在白名单中
        SysStudentWhitelist whitelist = whitelistService.selectWhitelistById(username);
        if (whitelist == null) {
            return "学号无效，请确认是否为本校学生";
        }

        // 基础非空与长度校验
        if (StringUtils.isEmpty(username)) {
            msg = "用户名不能为空";
        } else if (StringUtils.isEmpty(password)) {
            msg = "用户密码不能为空";
        } else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在5到20个字符之间";
        } else {
            // 检查学号是否已注册
            SysUser userCheck = new SysUser();
            userCheck.setUserName(username);
            if (userService.selectUserByStudentId(username) != null
                    || !userService.checkUserNameUnique(userCheck)) {
                msg = "该学号已被注册";
            } else {
                // 创建用户
                SysUser sysUser = new SysUser();
                sysUser.setUserName(username);
                sysUser.setStudentId(username);

                String inputNickName = registerBody.getNickName();
                if (StringUtils.isEmpty(inputNickName)) {
                    sysUser.setNickName("学生-" + username);
                } else {
                    sysUser.setNickName(inputNickName.trim());
                }

                String phonenumber = registerBody.getPhonenumber();
                if (StringUtils.isNotEmpty(phonenumber)) {
                    sysUser.setPhonenumber(phonenumber);
                }

                sysUser.setPassword(SecurityUtils.encryptPassword(password));
                sysUser.setStatus("0");
                sysUser.setDelFlag("0");
                sysUser.setPwdUpdateDate(DateUtils.getNowDate());

                //自动填充学院、专业
                sysUser.setDeptId(whitelist.getDeptId());
                sysUser.setMajor(whitelist.getMajor());

                // 设置学生角色
                SysRole studentRole = roleService.selectRoleByRoleKey("student");
                if (studentRole != null) {
                    sysUser.setRoleIds(new Long[]{studentRole.getRoleId()});
                }

                // 注册
                boolean regFlag = userService.registerUser(sysUser);
                if (!regFlag) {
                    msg = "注册失败，请联系管理员";
                } else {
                    // 绑定普通角色（保持原有逻辑）
                    SysRole commonRole = roleService.selectRoleByRoleKey("common");
                    if (commonRole != null) {
                        SysUserRole userRole = new SysUserRole();
                        userRole.setUserId(sysUser.getUserId());
                        userRole.setRoleId(commonRole.getRoleId());
                        userService.insertUserRole(userRole);
                    }

                    // 调用所有注册扩展
                    for (IUserRegisterExtension extension : registerExtensions) {
                        try {
                            extension.afterRegister(sysUser);
                        } catch (Exception e) {
                            // 可加日志
                        }
                    }

                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(
                            username, Constants.REGISTER, "注册成功"));
                }
            }
        }
        return msg;
    }
    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }

}
