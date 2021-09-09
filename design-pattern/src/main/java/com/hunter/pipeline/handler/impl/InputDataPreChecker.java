package com.hunter.pipeline.handler.impl;

import com.hunter.pipeline.handler.ContextHandler;
import com.hunter.pipeline.model.InstanceBuildContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 处理器 - 输入数据校验
 */
@Component
@Slf4j
public class InputDataPreChecker implements ContextHandler<InstanceBuildContext> {
    @Override
    public boolean handle(InstanceBuildContext context) {
        log.info("--输入数据校验--");

        Map<String, Object> formInput = context.getFormInput();

        if (MapUtils.isEmpty(formInput)) {
            context.setErrorMsg("表单输入数据不能为空");
            return false;
        }

        String instanceName = (String) formInput.get("instanceName");

        if (StringUtils.isEmpty(instanceName)) {
            context.setErrorMsg("表单输入数据必须包含实例名称");
            return false;
        }

        return true;
    }
}
