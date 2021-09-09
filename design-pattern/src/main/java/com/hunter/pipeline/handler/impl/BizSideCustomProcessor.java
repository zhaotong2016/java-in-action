package com.hunter.pipeline.handler.impl;


import com.hunter.pipeline.handler.ContextHandler;
import com.hunter.pipeline.model.InstanceBuildContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 业务方自定义数据处理
 */
@Slf4j
@Component
public class BizSideCustomProcessor implements ContextHandler<InstanceBuildContext> {


    @Override
    public boolean handle(InstanceBuildContext context) {
        log.info("--业务方自定义数据处理--");

        // 先判断是否存在自定义数据处理，如果没有，直接返回 true

        // 调用业务方的自定义的表单数据处理

        return true;
    }
}
