package com.hunter.pipeline.handler.impl;

import com.hunter.pipeline.handler.ContextHandler;
import com.hunter.pipeline.model.InstanceBuildContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 处理器 - 保存模型实例到相关DB表：
 */
@Slf4j
@Component
public class ModelInstanceSaver implements ContextHandler<InstanceBuildContext> {
    @Override
    public boolean handle(InstanceBuildContext context) {
        log.info("--保存模型实例到相关DB表--");

        // 假装保存模型实例

        return true;
    }
}
