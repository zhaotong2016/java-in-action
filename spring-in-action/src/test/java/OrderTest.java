import com.hunter.demo.Index;
import com.hunter.demo.bean.Order;
import com.hunter.demo.thread.OrderService;
import com.hunter.demo.transaction.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
//启动Spring
@SpringBootTest(classes = Index.class)
public class OrderTest {


    @Autowired
    private OrderService orderService;


    private static final int thread_num = 100;

    private CountDownLatch countDownLatch = new CountDownLatch(1);
    
    @Test
    public void test() {
        for (int i = 0; i < thread_num; i++) {
            final String code = "orderNo_" + i;

            Thread thread = new Thread(()->{
                try {
                   countDownLatch.await();
                    Map<String,Object> result = orderService.queryOrderInfo(code);
                    System.out.println("查询结果：" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
           countDownLatch.countDown();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test2(){


        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Order order = new Order();

            order.setOrderCode("orderNo_"+i);
            order.setSerialNo("serialNo_"+i);
            orders.add(order);
        }
        userMapper.insertUser(orders);
    }
}
