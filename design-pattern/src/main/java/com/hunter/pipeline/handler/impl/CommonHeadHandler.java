package com.hunter.pipeline.handler.impl;

import com.alibaba.fastjson.JSONObject;
import com.hunter.pipeline.handler.ContextHandler;
import com.hunter.pipeline.model.PipelineContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 通用处理
 * 比如我们想记录下每次管道处理的时间，以及在处理前和处理后都打印相关的日志。
 * 那么我们可以提供两个通用的 ContextHandler，分别放在每个管道的头和尾：
 */
@Slf4j
@Component
public class CommonHeadHandler  implements ContextHandler<PipelineContext> {


    @Override
    public boolean handle(PipelineContext context) {
        log.info("管道开始执行：context={}", JSONObject.toJSONString(context));
        // 设置开始时间
        context.setStartTime(LocalDateTime.now());
        return true;
    }
}