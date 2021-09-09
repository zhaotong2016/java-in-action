package com.hunter.pipeline.common;

public class CommonResponse<T> {

    public static CommonResponse<Long> success(Long instanceId) {
            return new CommonResponse<>();
    }

    public static CommonResponse<Long> failed(String errorMsg) {
        return new CommonResponse<>();
    }
}
