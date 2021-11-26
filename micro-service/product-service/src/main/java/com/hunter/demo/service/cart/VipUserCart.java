package com.hunter.demo.service.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * VIP用户：同样需要收取商品价格 10% 的快递费，但购买两件以上相同商品时，第三件开始享受一定折扣；
 * @author Hunter
 * @date 2021/11/26 20:19
 **/
public class VipUserCart {


    public Cart process(long userId, Map<Long, Integer> items) {

        Cart cart = new Cart();
        List<Item> itemList = new ArrayList<>();
        itemList.stream().forEach(item -> {
            //运费为商品总价的10%
            item.setDeliveryPrice(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())).multiply(new BigDecimal("0.1")));
            //购买两件以上相同商品，第三件开始享受一定折扣
            if (item.getQuantity() > 2) {
                item.setCouponPrice(item.getPrice()
                        .multiply(BigDecimal.valueOf(100 - Db.getUserCouponPercent(userId)).divide(new BigDecimal("100")))
                        .multiply(BigDecimal.valueOf(item.getQuantity() - 2)));
            } else {
                item.setCouponPrice(BigDecimal.ZERO);
            }
        });

        cart.setItems(itemList);
        return cart;
    }
}
