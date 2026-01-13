package com.ruoyi.framework.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysUserService;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private SysPermissionService permissionService;

    /*

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
        {
            SysUser user = userService.selectUserByUserName(username);

            if (StringUtils.isNull(user))
            {
                log.info("登录用户：{} 不存在.", username);
                throw new ServiceException(MessageUtils.message("user.not.exists"));
            }
            else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
            {
                log.info("登录用户：{} 已被删除.", username);
                throw new ServiceException(MessageUtils.message("user.password.delete"));
            }
            else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
            {
                log.info("登录用户：{} 已被停用.", username);
                throw new ServiceException(MessageUtils.message("user.blocked"));
            }

            passwordService.validate(user);

            return createLoginUser(user);
        }

        public UserDetails createLoginUser(SysUser user)
        {
            return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
        }
    }
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.warn("【DEBUG】接收到登录请求，用户名: '{}'", username);
        log.warn("【DEBUG】username 是否为数字? {}", StringUtils.isNumeric(username));

        SysUser user;

        if (StringUtils.isNumeric(username)) {
            user = userService.selectUserByStudentId(username);
            log.warn("【DEBUG】按学号查询结果: {}", user != null ? "找到用户 ID=" + user.getUserId() : "未找到");
            if (user == null) {
                user = userService.selectUserByUserName(username);
                log.warn("【DEBUG】学号未找到，改按用户名查询结果: {}", user != null ? "找到用户 ID=" + user.getUserId() : "未找到");
            }
        } else {
            user = userService.selectUserByUserName(username);
            log.warn("【DEBUG】按用户名查询结果: {}", user != null ? "找到用户 ID=" + user.getUserId() + ", user_name='" + user.getUserName() + "'" : "未找到");
        }

        if (StringUtils.isNull(user)) {
            log.warn("【ERROR】最终未找到用户！用户名: '{}'", username);
            throw new ServiceException(MessageUtils.message("user.not.exists"));
        } else if (UserStatus.DELETED.getCode().equals(user.getDelFlag())) {
            log.warn("【ERROR】用户已被删除: {}", username);
            throw new ServiceException(MessageUtils.message("user.password.delete"));
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.warn("【ERROR】用户已被停用: {}", username);
            throw new ServiceException(MessageUtils.message("user.blocked"));
        }

        passwordService.validate(user);
        return new LoginUser(user.getUserId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
    }
}
