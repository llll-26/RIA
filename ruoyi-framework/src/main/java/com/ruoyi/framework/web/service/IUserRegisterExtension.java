package com.ruoyi.framework.web.service;

import com.ruoyi.common.core.domain.entity.SysUser;


/**
 * 用户注册后的扩展处理（如创建积分、档案等）
 */

public interface IUserRegisterExtension {
    void afterRegister(SysUser user);
}
