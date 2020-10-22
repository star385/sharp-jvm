package com.sharpjvm.memory.model.config;

import java.io.IOException;
import java.util.Properties;

/**
 * 系统配置。
 *
 * User: zhuguoyin
 * Date: 13-3-8
 * To change this template use File | Settings | File Templates.
 */
public class SystemConfig {

    private static SystemConfig instance;

    Properties config;

    private SystemConfig() {
        config = new Properties();
        try {
            config.load(SystemConfig.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            System.out.println("未发现配置文件，利用默认设置！");
        }
    }

    public static SystemConfig getInstance() {
        if (instance == null) {
            instance = new SystemConfig();
        }
        return instance;
    }

    public String getHeapContainerClassName() {
        return config.getProperty("heap.container.class.name");
    }

    public String getConfigByKey(String key) {
        return config.getProperty(key);
    }
}
