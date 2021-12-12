package com.hunter.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hunter
 * @date 2021/12/11 23:28
 **/
@Service
public class RemoteService {


    public Map<String, Object> queryOrderInfo(String orderCode) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("orderCode",orderCode);
        map.put("money",Math.random());
        return map;

    }

    public List<Map<String,Object>> queryOrderInfoByCodeBatch(List<Map<String, String>> params) {

        List<Map<String,Object>> result = new ArrayList<>();
        for (Map<String, String> param : params) {

            String orderCode = param.get("orderCode");
            Map<String, Object> map = this.queryOrderInfo(orderCode);
            result.add(map);
        }

        return result;
    }
}
