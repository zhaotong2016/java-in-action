package com.hunter.pipeline.handler.impl;

import com.hunter.pipeline.handler.ContextHandler;
import com.hunter.pipeline.model.InstanceBuildContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 表单输入数据预处理
 */
@Slf4j
@Component
public class FormInputPreprocessor implements ContextHandler<InstanceBuildContext> {
    @Override
    public boolean handle(InstanceBuildContext context) {
        log.info("--表单输入数据预处理--");

        // 假装进行表单输入数据预处理

        return true;
    }
}
