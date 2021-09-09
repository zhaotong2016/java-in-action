package com.hunter.pipeline.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 传递到管道的上下文
 */
@Setter
@Getter
public class PipelineContext {


    private LocalDateTime startTime;

    private LocalDateTime endTime;

    /**
     * 获取数据名称
     */
    public String getName(){
        return this.getClass().getSimpleName();
    }


}
