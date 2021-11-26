package com.hunter.demo.service.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 如果我们熟记抽象类和抽象方法的定义的话，这时或许就会想到，是否可以把重复的逻辑定义在抽象类中，
 * 三个购物车只要分别实现不同的那份逻辑呢？其实，这个模式就是模板方法模式。
 * 我们在父类中实现了购物车处理的流程模板，然后把需要特殊处理的地方留空白也就是留抽象方法定义，
 * 让子类去实现其中的逻辑。由于父类的逻辑不完整无法单独工作，因此需要定义为抽象类。
 * @author Hunter
 * @date 2021/11/26 20:35
 **/

public abstract class AbstractCart {
    //处理购物车的大量重复逻辑在父类实现
    public Cart process(long userId, Map<Long, Integer> items) {

        Cart cart = new Cart();

        List<Item> itemList = new ArrayList<>();
        items.entrySet().stream().forEach(entry -> {
            Item item = new Item();
            item.setId(entry.getKey());
            item.setPrice(Db.getItemPrice(entry.getKey()));
            item.setQuantity(entry.getValue());
            itemList.add(item);
        });
        cart.setItems(itemList);
        //让子类处理每一个商品的优惠
        itemList.stream().forEach(item -> {
            processCouponPrice(userId, item);
            processDeliveryPrice(userId, item);
        });
        //计算商品总价
        cart.setTotalItemPrice(cart.getItems().stream().map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算总运费
        cart.setTotalDeliveryPrice(cart.getItems().stream().map(Item::getDeliveryPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算总折扣
        cart.setTotalDiscount(cart.getItems().stream().map(Item::getCouponPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        //计算应付价格
        cart.setPayPrice(cart.getTotalItemPrice().add(cart.getTotalDeliveryPrice()).subtract(cart.getTotalDiscount()));
        return cart;
    }

    //处理商品优惠的逻辑留给子类实现
    protected abstract void processCouponPrice(long userId, Item item);
    //处理配送费的逻辑留给子类实现
    protected abstract void processDeliveryPrice(long userId, Item item);
}