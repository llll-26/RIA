/*
package com.ruoyi.student.web.controller.auth;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.student.domain.vo.UpdateUserInfoVO;
import com.ruoyi.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/prod-api")
public class ProfileController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    @PostMapping("/user/update-info")
    public AjaxResult updateInfo(@RequestBody UpdateUserInfoVO vo) {
        userService.updateUserInfo(vo);
        return AjaxResult.success("修改成功");
    }

    @GetMapping("/user/profile")
    public AjaxResult profile(HttpServletRequest request) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        return AjaxResult.success(user);
    }
    //头像上传
    @PostMapping("/avatar/upload")
    public AjaxResult uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            // 获取当前登录用户（从缓存中）
            LoginUser loginUser = SecurityUtils.getLoginUser();
            SysUser currentUser = loginUser.getUser();

            //上传文件
            String filePath = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);

            //更新数据库
            currentUser.setAvatar(filePath);
            userService.updateUser(currentUser);

            //更新缓存中的用户信息，并重新写入 Redis
            loginUser.getUser().setAvatar(filePath); // 同步到缓存对象
            tokenService.setLoginUser(loginUser);    // 刷新 Redis 缓存

            return AjaxResult.success(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("上传失败: " + e.getMessage());
        }
    }

}
*/
