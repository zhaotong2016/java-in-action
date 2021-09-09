package com.hunter.demo.thread;

import com.hunter.demo.bean.Order;
import com.hunter.demo.transaction.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RemoteServiceCall {

    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> queryOrderInfoByCode(String orderCode) {
        return null;
    }


    public List<Map<String, Object>> queryOrderInfoByCodeBatch(  List<Map<String,String>> params) {


        List<Map<String, Object>> maps = new ArrayList<>();

        params.forEach(stringStringMap -> {

            String orderCode = stringStringMap.get("orderCode");
            Order order = userMapper.getOrder(orderCode);
            String serialNo = stringStringMap.get("serialNo");

            Map<String,Object> map = new HashMap<>();
            map.put("serialNo",serialNo);
            map.put("order",order);
            maps.add(map);
        });

            return maps;

    }

}
