package com.marketlogic.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties("elasticsearch")
public class ElasticsearchProperties {
    private String[] hostAndPort;
    private Duration socketTimeout;
    private Duration connectTimeout;
}