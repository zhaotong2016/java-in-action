package com.hunter.demo.controller;

import com.hunter.demo.domain.PluginConfig;
import com.hunter.demo.factory.DefaultSpringPluginFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/plugin")
public class PluginController {

    @Autowired
    private DefaultSpringPluginFactory pluginFactory;

    @RequestMapping(value = "list")
    public  Collection<PluginConfig> getHavePlugins(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Collection<PluginConfig> configs = pluginFactory.flushConfigs();
        return configs;
    }

    @RequestMapping(value = "active")
    public void activePlugin(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String id = req.getParameter("id");
        pluginFactory.activePlugin(id);
        resp.getWriter().append("active success");
    }
}
