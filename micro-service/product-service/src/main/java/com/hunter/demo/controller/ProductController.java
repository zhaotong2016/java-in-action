package com.hunter.demo.controller;

import com.hunter.demo.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("product")
public class ProductController {


    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "/balance/{userId}/{productId}/{amount}", method = RequestMethod.GET)
    public ResultMessage test(@PathVariable("userId") Long userId,
                       @PathVariable("productId") Integer productId,
                       @PathVariable("amount") BigDecimal amount){

        log.info("扣减余额");

        String url = "http://FUND/fund/account/balance/{userId}/{amount}";

        Map<String,Object> params = new HashMap();
        params.put("userId",userId);
        params.put("amount",amount);


        ResultMessage resultMessage =
                restTemplate.postForObject(url,null, ResultMessage.class,params);

        log.info("响应：{}",resultMessage.toString());

        return resultMessage;
    }
}
