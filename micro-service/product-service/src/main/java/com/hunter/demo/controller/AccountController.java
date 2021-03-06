package com.hunter.demo.controller;

import com.hunter.demo.ResultMessage;
import com.hunter.demo.annotaion.Desensitized;
import com.hunter.demo.domain.CustomerBaseInfo;
import com.hunter.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("fund")
public class AccountController {

    private static ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

    public static ThreadLocal<Boolean> getThreadLocal() {
        return threadLocal;
    }

    @Autowired
    private ProductService productService;
    /**
     * 扣除资金
     * @param userId
     * @param amount
     * @return
     */
    @RequestMapping(value = "/account/balance/{userId}/{amount}", method = RequestMethod.POST)
    public ResultMessage test(@PathVariable("userId") Long userId,
                              @PathVariable("amount") BigDecimal amount, HttpServletRequest request){
        log.info("服务端口：{}，扣除账户：{}，资金：{}" ,request.getServerPort(), userId,amount );

        return ResultMessage.suc();
    }

    @RequestMapping(value = "testDec", method = RequestMethod.POST)
    public ResultMessage testDec(@RequestParam("isDesensitization") boolean isDesensitization){

        threadLocal.set(isDesensitization);
        CustomerBaseInfo customerBaseInfo = new CustomerBaseInfo();

        customerBaseInfo.setAccountNo("HK82345678-100");
        customerBaseInfo.setUserName("测试转账客户");
        customerBaseInfo.setDesensitization(isDesensitization);
        return ResultMessage.suc(customerBaseInfo);
    }

    @RequestMapping(value = "query", method = RequestMethod.GET)
    public ResultMessage query(){
        String result = productService.query();
        return ResultMessage.suc(result);
    }

    @RequestMapping(value = "query1", method = RequestMethod.GET)
    public ResultMessage query1(){
        String result = productService.query1();
        return ResultMessage.suc(result);
    }

    @RequestMapping(value = "query2", method = RequestMethod.GET)
    public ResultMessage query2(){
        String result = productService.query2();
        return ResultMessage.suc(result);
    }


    @RequestMapping(value = "use", method = RequestMethod.GET)
    public ResultMessage useThreadQuery(){
         productService.product(10);
        productService.cosumer();
        return ResultMessage.suc(true);
    }




    public static void main(String[] args) {
        getApiList();
    }


    public static void getApiList(){
        String url = "http://47.242.255.157/api/deploy/list";

        try {

            //用map来存储数据
            Map<String,String> map=new HashMap();

            map.put("token","71b78aa0-927c-4272-b11d-a1b5479e86a5");
            map.put("user","zhao.hui");
            map.put("location","local");
            map.put("Content-Type",
                    "application/json;charset=utf-8");




            //登录，获取cookies
           /* Connection.Response login=Jsoup.connect(url).headers(map)
                    .ignoreContentType(true)
                    .followRedirects(true)
                    .data(map)
                    .method(Connection.Method.POST)
                    .execute()
                    .charset("utf-8");

            //模拟成功后，自己想获取的数据，并且要求携带cookies数据
            Document document=Jsoup.connect(url).cookies(login.cookies()).get();
            //打印数据网页
            System.out.println(login.body());*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void loginIndex(){
        String url = "http://47.242.255.157/deploy/index.html";

        try {

            String loginUrl = "http://47.242.255.157/api/user/deploy/login";

            //用map来存储数据
            Map<String,String> map=new HashMap();

            map.put("token","71b78aa0-927c-4272-b11d-a1b5479e86a5");
            map.put("user","zhao.hui");

            //登录，获取cookies
           /* Connection.Response login=Jsoup.connect(loginUrl).headers(map)
                    .ignoreContentType(true)
                    .followRedirects(true)
                    .data(map)
                    .method(Connection.Method.POST)
                    .execute()
                    .charset("utf-8");


                    //模拟成功后，自己想获取的数据，并且要求携带cookies数据
            Document document=Jsoup.connect(url).cookies(login.cookies()).get();
            //打印数据网页
            System.out.println(login.body());*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
