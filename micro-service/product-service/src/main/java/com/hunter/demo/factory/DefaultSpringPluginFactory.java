package com.hunter.demo.factory;

import com.alibaba.fastjson.JSON;
import com.hunter.demo.domain.PluginConfig;
import com.hunter.demo.domain.Plugins;
import org.aspectj.util.FileUtil;
import org.aspectj.weaver.Advice;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Component
public class DefaultSpringPluginFactory implements ApplicationContextAware {

    private static final String configPath = "plugins.config";

    private ApplicationContext applicationContext;

    private Map<String, PluginConfig> configs = new HashMap<>();

    private Map<String, Advice> adviceCache = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void activePlugin(String pluginId){
        if (!adviceCache.containsKey(pluginId)){
            throw new RuntimeException("");
        }
        PluginConfig pluginConfig = configs.get(pluginId);
        pluginConfig.setActive(true);

    }


    public Collection<PluginConfig> flushConfigs() throws IOException {
        File config = new File(configPath);
        String configJson = FileUtil.readAsString(config);

        Plugins pluginConfigs = JSON.parseObject(configJson,Plugins.class);

        for (PluginConfig pluginConfig : pluginConfigs.getConfigList()) {
            if (configs.get(pluginConfig.getId()) == null){
                configs.put(pluginConfig.getId(),pluginConfig);
            }
        }

        return configs.values();
    }
}
