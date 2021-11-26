package com.hunter.demo.service.cart;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 购物车里面商品
 * @author Hunter
 * @date 2021/11/26 20:16
 **/
@Data
public class Item {
    //商品ID
    private long id;
    //商品数量
    private int quantity;
    //商品单价
    private BigDecimal price;
    //商品优惠
    private BigDecimal couponPrice;
    //商品运费
    private BigDecimal deliveryPrice;
}
