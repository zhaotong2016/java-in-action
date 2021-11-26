package com.hunter.demo.controller;

import com.hunter.demo.service.cart.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Hunter
 * @date 2021/11/26 20:30
 **/
@RestController
public class CarController {


    @GetMapping("wrong")
    public Cart wrong(@RequestParam("userId") int userId) {
        //根据用户ID获得用户类型
        String userCategory = Db.getUserCategory(userId);
        //普通用户处理逻辑
        if (userCategory.equals("Normal")) {
            NormalUserCart normalUserCart = new NormalUserCart();
            return normalUserCart.process(userId, new HashMap<>());
        }
        //VIP用户处理逻辑
        if (userCategory.equals("Vip")) {
            VipUserCart vipUserCart = new VipUserCart();
            return vipUserCart.process(userId, new HashMap<>());
        }
        //内部用户处理逻辑
        if (userCategory.equals("Internal")) {
            InternalUserCart internalUserCart = new InternalUserCart();
            return internalUserCart.process(userId, new HashMap<>());
        }

        return null;
    }
}
