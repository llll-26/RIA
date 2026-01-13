/*
package com.ruoyi.web.util;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class ChatUtil {
    private static ChatModel model;

    */
/**
     * 使用静态初始化块，为私有的静态成员变量model初始化值
     * 单例
     *//*

    static {
        model = OpenAiChatModel.builder()
                .baseUrl("https://api.deepseek.com/v1")
                .apiKey("sk-55f5f67d3d334362a96ae21c52f6432c")
                .modelName("deepseek-chat")
                .build();
    }

    */
/**
     * 获取AiService代理对象
     * @param clazz
     * @return
     * @param <T>
     *//*

    public static <T> T getService(Class<T> clazz) {
        return AiServices.create(clazz, model);
    }
}
*/
