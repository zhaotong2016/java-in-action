package com.hunter.pipeline.handler.impl;


import com.alibaba.fastjson.JSONObject;
import com.hunter.pipeline.handler.ContextHandler;
import com.hunter.pipeline.model.PipelineContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class CommonTailHandler implements ContextHandler<PipelineContext> {


    @Override
    public boolean handle(PipelineContext context) {
        // 设置处理结束时间
        context.setEndTime(LocalDateTime.now());

        log.info("管道执行完毕：context={}", JSONObject.toJSONString(context));

        return true;
    }
}