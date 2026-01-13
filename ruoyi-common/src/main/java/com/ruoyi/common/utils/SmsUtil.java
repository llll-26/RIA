//package com.ruoyi.common.utils;
//
//import com.aliyun.dysmsapi20170525.Client;
//import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
//import com.aliyun.teaopenapi.models.Config;
//
//public class SmsUtil {
//
//    private static final String ACCESS_KEY_ID = "your-access-key-id";
//    private static final String ACCESS_KEY_SECRET = "your-access-key-secret";
//
//    public static void sendSms(String phone, String code) {
//        try {
//            // 1. 创建客户端
//            Config config = new Config()
//                    .setAccessKeyId(ACCESS_KEY_ID)
//                    .setAccessKeySecret(ACCESS_KEY_SECRET);
//            config.endpoint = "dysmsapi.aliyuncs.com";
//            Client client = new Client(config);
//
//            // 2. 构造请求
//            SendSmsRequest request = new SendSmsRequest();
//            request.phoneNumbers = phone;
//            request.templateParam = "{\"code\":\"" + code + "\"}";
//
//            // 3. 发送短信
//            client.sendSms(request);
//
//            System.out.println("【短信发送成功】手机号: " + phone + ", 验证码: " + code);
//
//        } catch (Exception e) {
//            System.err.println("【短信发送失败】手机号: " + phone);
//            e.printStackTrace(); // 打印错误详情（部署时可去掉）
//        }
//    }
//}
