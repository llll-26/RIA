package com.ruoyi.student.member.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class ProofTokenUtil {

    // 生成防伪令牌：base64(用户ID|总时长|生成时间)
    public static String generateToken(Long userId, int totalHours, String secretKey) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String payload = userId + "|" + totalHours + "|" + timestamp;

        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(keySpec);
            byte[] signature = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));

            String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes(StandardCharsets.UTF_8));
            String encodedSignature = Base64.getUrlEncoder().withoutPadding().encodeToString(signature);

            return encodedPayload + "." + encodedSignature;
        } catch (Exception e) {
            throw new RuntimeException("生成防伪令牌失败", e);
        }
    }

    // 验证令牌是否合法
    public static ProofData verifyToken(String token, String secretKey) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 2) return null;

            String payload = new String(Base64.getUrlDecoder().decode(parts[0]), StandardCharsets.UTF_8);
            byte[] signature = Base64.getUrlDecoder().decode(parts[1]);

            // 重新计算签名
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(keySpec);
            byte[] expectedSignature = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));

            if (!MessageDigest.isEqual(expectedSignature, signature)) {
                return null; // 签名不匹配
            }

            // 解析 payload
            String[] fields = payload.split("\\|");
            if (fields.length != 3) return null;

            return new ProofData(
                    Long.parseLong(fields[0]),
                    Integer.parseInt(fields[1]),
                    fields[2]
            );
        } catch (Exception e) {
            return null;
        }
    }

    // 数据载体
    public static class ProofData {
        public final Long userId;
        public final int totalHours;
        public final String generatedAt;

        public ProofData(Long userId, int totalHours, String generatedAt) {
            this.userId = userId;
            this.totalHours = totalHours;
            this.generatedAt = generatedAt;
        }
    }
}
