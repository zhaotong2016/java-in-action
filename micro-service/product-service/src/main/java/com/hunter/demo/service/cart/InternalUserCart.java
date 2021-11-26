package com.hunter.demo.service.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 内部用户：可以免运费，无商品折扣。
 * @author Hunter
 * @date 2021/11/26 20:22
 **/
public class InternalUserCart {



    public Cart process(long userId, Map<Long, Integer> items) {

        Cart cart = new Cart();
        List<Item> itemList = new ArrayList<>();
        itemList.stream().forEach(item -> {
            //免运费
            item.setDeliveryPrice(BigDecimal.ZERO);
            //无优惠
            item.setCouponPrice(BigDecimal.ZERO);
        });
        cart.setItems(itemList);
        return cart;
    }
}
