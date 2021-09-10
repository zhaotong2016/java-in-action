package com.hunter.demo.factory;

import com.alibaba.fastjson.JSON;
import com.hunter.demo.domain.PluginConfig;
import com.hunter.demo.domain.Plugins;
import org.aspectj.util.FileUtil;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Component
public class DefaultSpringPluginFactory implements ApplicationContextAware {

    private static final String configPath = "classpath:plugins.config";

    private ApplicationContext applicationContext;

    private Map<String, PluginConfig> configs = new HashMap<>();

    private Map<String, Advice> adviceCache = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public Advice buildAdvice(PluginConfig config) throws MalformedURLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        if (adviceCache.containsKey(config.getClassName())){
            return adviceCache.get(config.getClassName());
        }
        //获取本地待加载的jar插件包路径
        URL targetUrl = new URL(config.getJarRemoteUrl());

        //获取当前正在运行的项目，加载那些jar包，
        URLClassLoader loader  = (URLClassLoader)getClass().getClassLoader();

        boolean isLoader = false;

        for (URL url : loader.getURLs()) {
            if (url.equals(targetUrl)){ //判断当前待加载的jar包，是否已经被加载到loader
                isLoader = true;
                break;
            }
        }

        if (!isLoader){ //如果插件jar包没有被加载到loader,则调用add.invoke将jar加载进来
            Method add = URLClassLoader.class.
                    getDeclaredMethod("addURL", new Class[]{URL.class});
            add.setAccessible(true);
            add.invoke(loader,targetUrl); //加载本地jar文件
        }

        //将插件jar包里面的com.hunter.demo.config.CountTimesPlugin类，创建class对象
        Class<?> adviceClass = loader.loadClass(config.getClassName()); //Advice

        //adviceClass对象通过反射创建advice拦截
        adviceCache.put(adviceClass.getName(),(Advice) adviceClass.newInstance());


        return adviceCache.get(adviceClass.getName());//返回advice对象



    }
    public void activePlugin(String pluginId) {
        /*if (!adviceCache.containsKey(pluginId)) {
            throw new RuntimeException("");
        }*/
        PluginConfig config = configs.get(pluginId);
        config.setActive(true);

        for (String name : applicationContext.getBeanDefinitionNames()) {

            Object bean = applicationContext.getBean(name);

            if (bean == this){
                continue;
            }
            if (!(bean instanceof Advised)){
                continue;
            }

            if (findAdvice((Advised) bean,config.getClassName()) != null){
                continue;
            }

            Advice advice = null;

            try {
                advice = buildAdvice(config);
                ((Advised) bean).addAdvice(advice); //实现拦截
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    private Object findAdvice(Advised bean, String className) {
        return adviceCache.get(className);
    }


    public Collection<PluginConfig> flushConfigs() throws IOException {
        File config = ResourceUtils.getFile(configPath);
        String configJson = FileUtil.readAsString(config);

        Plugins pluginConfigs = JSON.parseObject(configJson, Plugins.class);

        for (PluginConfig pluginConfig : pluginConfigs.getConfigs()) {
            if (configs.get(pluginConfig.getId()) == null) {
                configs.put(pluginConfig.getId(), pluginConfig);
            }
        }

        return configs.values();
    }
}
