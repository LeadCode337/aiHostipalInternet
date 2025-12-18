package com.ruoyi.file.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: RuoYi-Cloud-v3.6.6
 * @BelongsPackage: com.ruoyi.hospital.config
 * @CreateTime: 2025-12-17 22:13
 * @Description: TODO
 * @editor:巴巴巴巴子
 */
@Component
@ConfigurationProperties(prefix = "file")
public class FileConfig {
    // 对应 Nacos 中的 file.path
    private String path;

    // Getter + Setter
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
