package com.hunter.pipeline.service;

import com.hunter.pipeline.common.CommonResponse;
import com.hunter.pipeline.common.InstanceBuildRequest;

public interface ModelService {

     CommonResponse<Long> buildModelInstance(InstanceBuildRequest request);
}
