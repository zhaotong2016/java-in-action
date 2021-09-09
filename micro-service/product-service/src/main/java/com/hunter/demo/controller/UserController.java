package com.hunter.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam("c") Integer c){
        System.out.println("=====>:c" + c);
        return "OK";
    }
}
