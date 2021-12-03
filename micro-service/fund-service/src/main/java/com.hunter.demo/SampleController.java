package com.hunter.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hunter
 * @date 2021/12/03 23:29
 **/
@RestController
public class SampleController {

    @Autowired
    private SampleService sampleService;


    @GetMapping("sample")
    public String getSample(){
        return sampleService.service();
    }
}
