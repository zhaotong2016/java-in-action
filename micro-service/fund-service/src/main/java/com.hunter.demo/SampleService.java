package com.hunter.demo;

import com.demo.hunter.MethodExecuteLog;
import org.springframework.stereotype.Service;

/**
 * @author Hunter
 * @date 2021/12/03 23:26
 **/
@Service
public class SampleService {

    @MethodExecuteLog
    public String service(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello";
    }
}
