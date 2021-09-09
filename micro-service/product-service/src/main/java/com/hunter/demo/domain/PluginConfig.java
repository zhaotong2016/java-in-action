package com.hunter.demo.domain;


import lombok.Data;

@Data
public class PluginConfig {
    private boolean active;
    private String className;
    private String id;
    private String jarRemoteUrl;
    private String name;
}
