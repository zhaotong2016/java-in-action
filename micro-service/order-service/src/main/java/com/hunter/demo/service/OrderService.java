package com.hunter.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Hunter
 * @date 2021/12/11 23:28
 **/
@Slf4j
@Service
public class OrderService {

    class Request{
        String orderCode;
        String serialNo;
        CompletableFuture<Map<String,Object>> future;
    }

    @Autowired
    private RemoteService remoteService;

    LinkedBlockingDeque<Request> queue = new LinkedBlockingDeque<Request>();

    public Map<String,Object> queryOrderInfo(String orderCode) throws ExecutionException, InterruptedException {

        String serialNo = UUID.randomUUID().toString();

        CompletableFuture<Map<String,Object>> future = new CompletableFuture<>();


        Request request = new Request();
        request.orderCode = orderCode;
        request.serialNo = serialNo;
        request.future = future;

        //return remoteService.queryOrderInfo(orderCode);
        log.info("======" + request.toString());
        queue.add(request);

        return future.get();
    }


    @PostConstruct
    public void init(){
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int size = queue.size();

                if (size == 0){
                    return;
                }
                log.info("the numner is2 ==" +size);
                List<Map<String,String>> params = new ArrayList<>();
                List<Request> requests = new ArrayList<>();
                //把队列数据获取，拼成一批
                for (int i = 0; i < size; i++) {
                    Request request = queue.poll();
                    Map<String,String> map = new HashMap<>();
                    map.put("orderCode",request.orderCode);
                    map.put("serialNo",request.serialNo);
                    requests.add(request);
                    params.add(map);
                }

                List<Map<String, Object>> responses = remoteService.queryOrderInfoByCodeBatch(params);
                log.info("the requests is1 ==" +requests.toString());
                log.info("the requests is2 ==" +params.toString());
                for (Request request : requests) {
                    String serialNo = request.serialNo;
                    for (Map<String, Object> res : responses) {
                        if (serialNo.equals(res.get("serialNo").toString())){
                            request.future.complete(res);
                            break;
                        }
                    }
                }
            }
        },8,10,TimeUnit.MICROSECONDS);
    }
}
