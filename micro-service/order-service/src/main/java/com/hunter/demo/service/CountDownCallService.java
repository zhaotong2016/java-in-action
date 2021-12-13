package com.hunter.demo.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author Hunter
 * @date 2021/12/11 23:51
 **/
@Slf4j
@Service
public class CountDownCallService {

    //并发数
    private static final int thread_num = 1000;

    private CountDownLatch countDownLatch = new CountDownLatch(1);//花名册1000

    @Autowired
    private OrderService orderService;

    public void testInterface1() throws ExecutionException, InterruptedException {
        for (int i = 0; i < thread_num; i++) {
            final String code = "code-" + (i + 1);
            Map<String, Object> result = orderService.queryOrderInfo(code);
            log.info("查询结果：" +result);
        }

    }

    public void testInterface() throws InterruptedException {
        for (int i = 0; i < thread_num; i++) {
            final String code = "code-" + (i+1);
            Thread thread = new Thread(()->{

                try {
                    countDownLatch.await();
                    Map<String, Object> result = orderService.queryOrderInfo(code);
                    log.info("查询结果：" +result);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            thread.start();

        }
        countDownLatch.countDown();
    }


}
