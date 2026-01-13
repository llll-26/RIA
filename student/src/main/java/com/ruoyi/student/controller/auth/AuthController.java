/*
package com.ruoyi.student.web.controller.auth;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.SysPermissionService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.student.domain.vo.AuthRegisterVO;
import com.ruoyi.student.domain.vo.AuthLoginVO;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/prod-api")
public class AuthController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;
    // 在 AuthController 类中添加如下属性注入
    @Autowired
    private SysPermissionService permissionService;

    // 注册
    @PostMapping("/register")
    public AjaxResult register(@RequestBody AuthRegisterVO vo) {
        // 👇 打印收到的数据
        System.out.println("收到注册请求: studentId=" + vo.getStudentId() + ", password=" + vo.getPassword());

        if (StringUtils.isEmpty(vo.getStudentId()) || StringUtils.isEmpty(vo.getPassword())) {
            return AjaxResult.error("学号和密码不能为空");
        }

        if (userService.selectUserByStudentId(vo.getStudentId()) != null) {
            return AjaxResult.error("该学号已被注册");
        }

        SysUser user = new SysUser();
        user.setUserName(vo.getStudentId());
        user.setStudentId(vo.getStudentId());
        user.setPhonenumber(vo.getMobile());
        user.setPassword(SecurityUtils.encryptPassword(vo.getPassword()));
        user.setStatus("0");
        user.setNickName(StringUtils.isNotEmpty(vo.getUsername()) ? vo.getUsername() : vo.getStudentId());

        userService.insertUser(user);

        Map<String, Object> data = new HashMap<>();
        data.put("userId", user.getUserId());
        return AjaxResult.success("注册成功", data);
    }

    // 登录
    @PostMapping("/login")
    public AjaxResult login(@RequestBody AuthLoginVO vo) {

        SysUser user = userService.selectUserByStudentId(vo.getStudentId());
        if (user == null) {
            return AjaxResult.error(401, "用户不存在");
        }
        if (!SecurityUtils.matchesPassword(vo.getPassword(), user.getPassword())) {
            return AjaxResult.error(401, "密码错误");
        }

        // 将 SysUser 转换为 LoginUser
        LoginUser loginUser = new LoginUser(
                user.getUserId(),           // ← 必须传 userId！
                user.getDeptId(),
                user,
                permissionService.getMenuPermission(user) // 建议加上权限
        );
        // 可根据需要补充其他字段如 permissions、roles 等

        String token = tokenService.createToken(loginUser);
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        return AjaxResult.success("登录成功", data);
    }


    // 获取用户信息
    @GetMapping("/info")
    public AjaxResult getInfo() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Map<String, Object> data = new HashMap<>();
        data.put("userId", user.getUserId());
        data.put("studentId", user.getStudentId());
        data.put("nickName", user.getNickName());
        data.put("mobile", user.getPhonenumber());
        return AjaxResult.success(data);
    }
}
*/
