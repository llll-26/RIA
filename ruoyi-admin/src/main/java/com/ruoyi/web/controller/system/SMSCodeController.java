package com.ruoyi.web.controller.system;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.WxUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author huangjie
 * @date 2025/11/18
 *
 */
 @RestController
 public class SMSCodeController {
    @Autowired
    private RedisCache redisCache;


    /**
     * 发送短信验证码
     * * @param mobile手机号
     *
     * @return短信验证码
     */
    @GetMapping("/sendCodeNew")
    public AjaxResult sendCodeNew(String phone){
        //0.校验手机号
        if(!phone.matches(CacheConstants.PH0NE_REGEX)){
            return AjaxResult.error("手机号格式错误");
        }

        //1.从redis中获取验证码
        String codeInRedis = redisCache.getCacheObject(CacheConstants.SMS_CODE_PRE + phone);
        //2.判断验证码是否存在
        String code = null;
        String redisValue=null;
        if(StringUtils.isEmpty(codeInRedis)){
            //2.1没查到  .生成一个6位数由0-9组成的验证码
            //并加上当前时间戳
            redisValue=code+","+System.currentTimeMillis();//142456,1699456000000
            System.out.println("验证码为：------"+code);
        }else {
            //2.2 查询到
            //取得验证码
            code =  codeInRedis.split(",")[0];//通过分割取得验证码
            //取得上次发送时间
            long lastSendTime = Long.parseLong(codeInRedis.split(",")[1]);
            //2.2.1 是否过重发时间
            if(System.currentTimeMillis()-lastSendTime<=30*1000){
                //没过
                return  AjaxResult.error("非法请求！");
            }
            //2.2.2 过了时间，刷新验证码的时间
            redisValue=code+","+System.currentTimeMillis();//142456,1699456000000

        }
        //3.把验证码缓存到服务端-redis    键：由手机号+前缀拼接  值：验证码  过期时间：2分钟
        redisCache.setCacheObject(CacheConstants.SMS_CODE_PRE+phone,redisValue,5, TimeUnit.MINUTES);

        //4.响应结果
        return AjaxResult.success();
    }
}
