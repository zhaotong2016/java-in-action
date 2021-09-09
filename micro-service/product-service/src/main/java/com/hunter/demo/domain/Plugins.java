package com.hunter.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Plugins implements Serializable {

    private String name;
    private List<PluginConfig> configList;
}
