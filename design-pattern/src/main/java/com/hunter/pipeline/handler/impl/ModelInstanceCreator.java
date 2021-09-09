package com.hunter.pipeline.handler.impl;

import com.hunter.pipeline.handler.ContextHandler;
import com.hunter.pipeline.model.InstanceBuildContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 处理器 - 根据输入创建模型实例
 */
@Slf4j
@Component
public class ModelInstanceCreator implements ContextHandler<InstanceBuildContext> {
    @Override
    public boolean handle(InstanceBuildContext context) {
        log.info("--根据输入数据创建模型实例--");

        // 假装创建模型实例

        return true;
    }
}
