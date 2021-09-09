package com.hunter.pipeline.service.impl;

import com.hunter.pipeline.common.CommonResponse;
import com.hunter.pipeline.common.InstanceBuildRequest;
import com.hunter.pipeline.executor.PipelineExecutor;
import com.hunter.pipeline.model.InstanceBuildContext;
import com.hunter.pipeline.service.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 只负责处理请求，不用关心具体的业务逻辑
 */
@Slf4j
@Service
public class ModelServiceImpl implements ModelService {


    @Autowired
    private PipelineExecutor pipelineExecutor;

    @Override
    public CommonResponse<Long> buildModelInstance(InstanceBuildRequest request) {
        InstanceBuildContext data = createPipelineData(request);
        boolean success = pipelineExecutor.acceptSync(data);

        // 创建模型实例成功
        if (success) {
            return CommonResponse.success(data.getInstanceId());
        }

        log.error("创建模式实例失败：{}", data.getErrorMsg());
        return CommonResponse.failed(data.getErrorMsg());
    }

    private InstanceBuildContext createPipelineData(InstanceBuildRequest request) {
        InstanceBuildContext buildContext = new InstanceBuildContext();
        return buildContext;
    }

    /**
     * 提交模型（构建模型实例）
     * 输入数据校验 -> 根据输入创建模型实例 -> 保存模型实例到相关 DB 表
     */
//    @Override
//    public CommonResponse<Long> buildModelInstance(InstanceBuildRequest request) {
//        // 输入数据校验
//        validateInput(request);
//        // 根据输入创建模型实例
//        ModelInstance instance = createModelInstance(request);
//        // 保存实例到相关 DB 表
//        saveInstance(instance);
//    }




}
