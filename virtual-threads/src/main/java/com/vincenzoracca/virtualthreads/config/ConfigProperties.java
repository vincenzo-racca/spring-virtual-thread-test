package com.vincenzoracca.virtualthreads.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
@Data
public class ConfigProperties {

    private String mockclientUrl;
}
