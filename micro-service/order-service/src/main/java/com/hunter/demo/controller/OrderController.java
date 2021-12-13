package com.hunter.demo.controller;

import com.hunter.demo.service.CountDownCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hunter
 * @date 2021/12/13 11:18
 **/
@RestController
public class OrderController {

    @Autowired
    private CountDownCallService countDownCallService;


    @RequestMapping("callOrder")
    public String callOrder(){
        try {
            countDownCallService.testInterface();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

}
