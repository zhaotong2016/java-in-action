package com.hunter.demo.service.cart;

import java.math.BigDecimal;

/**
 * @author Hunter
 * @date 2021/11/26 20:26
 **/
public class Db {

    public static BigDecimal getItemPrice(long key){
        return BigDecimal.valueOf(10);
    }

    public static Integer getUserCouponPercent(long userId){
        return Integer.valueOf(10);
    }

    public static String getUserCategory(int userId) {
        return "Normal";
    }
}
