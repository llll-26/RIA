package com.ruoyi.student.member.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProofConfig {
    @Value("${proof.secret-key}")
    private String secretKey;

    @Bean
    public String proofSecretKey() {
        return secretKey;
    }
}
