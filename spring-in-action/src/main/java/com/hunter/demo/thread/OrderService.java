package com.hunter.demo.thread;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.*;

@Service
public class OrderService {

    class Request {
        private String orderCode;
        private String serialNo;
        private CompletableFuture<Map<String,Object>> future;
    }


    @Autowired
    RemoteServiceCall remoteServiceCall;

    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    public Map<String,Object> queryOrderInfo(String orderCode)  throws InterruptedException, ExecutionException {

        CompletableFuture<Map<String,Object>> future = new CompletableFuture<>();

        Request request = new Request();
        request.orderCode = orderCode;
        request.serialNo = UUID.randomUUID().toString();
        request.future = future;
        queue.add(request);
        System.out.println("queryOrderInfo==================>" + request);
        //return remoteServiceCall.queryOrderInfoByCode(orderCode);
        return future.get();
    }

    @PostConstruct
    public void init(){
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (queue.size() == 0){
                    return;
                }
                System.out.println("the number is size: " + queue.size());
               List<Map<String,String>> params = new ArrayList<>();
                List<Request> requests = new ArrayList<>();
                for (int i = 0; i < queue.size() ; i++) {
                    Request request = queue.poll();
                    Map<String,String> map = new HashMap<>();
                    map.put("orderCode",request.orderCode);
                    map.put("serialNo",request.serialNo);
                    params.add(map);

                    requests.add(request);
                }
                //批量调用

                List<Map<String, Object>>  responses = remoteServiceCall.queryOrderInfoByCodeBatch(params);
                System.out.println("responses====================================>");
                for (Request request : requests) {
                    String serialNo = request.serialNo;

                    for (Map<String, Object> res : responses) {
                        if (serialNo.equals(res.get("serialNo"))){
                            //获取结果 future.get();阻塞 -》通知
                            request.future.complete(res);
                            break;
                        }
                    }
                }
            }
        },0,10,TimeUnit.MILLISECONDS);
    }


}
