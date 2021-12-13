import com.hunter.demo.OrderApplication;
import com.hunter.demo.service.CountDownCallService;
import com.hunter.demo.service.OrderService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

/**
 * @author Hunter
 * @date 2021/12/13 10:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class Test {

    @Autowired
    private CountDownCallService countDownCallService;


    @org.junit.Test
    public void tests() throws ExecutionException, InterruptedException {
        countDownCallService.testInterface();
    }
}
