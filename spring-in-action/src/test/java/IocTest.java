import com.hunter.demo.config.AppConfig;
import com.hunter.demo.ext.core.MyMapperProxy;
import com.hunter.demo.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

public class IocTest extends DefaultListableBeanFactory{


    @Test
    public void test(){
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);






       //动态代理
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance( this.getClass().getClassLoader(),
                new Class[]{UserMapper.class},new MyMapperProxy());

        userMapper.getUser(11);

    }


    public static class AService{
        private String id;

        private BService b;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public BService getB() {
            return b;
        }

        public void setB(BService b) {
            this.b = b;
        }
    }

    public static class BService{
        private AService a;

        public AService getA() {
            return a;
        }

        public void setA(AService a) {
            this.a = a;
        }
    }
    public static class AService$proxy extends AService{
        AService target;

        public AService$proxy(AService target) {
            this.target = target;
        }
    }
    @Test
    public void createBeanTest(){

        IocTest beanFactory = new IocTest();

        //1、实例化AbstractAutowireCapableBeanFactory.createBeanInstance
        //2、属性填充AbstractAutowireCapableBeanFactory.populateBean
        //3、后置处理AbstractAutowireCapableBeanFactory.initializeBean
        RootBeanDefinition rbd = new RootBeanDefinition(AService.class);
        rbd.setPropertyValues(new MutablePropertyValues().add("id","88898"));

        //AbstractAutowireCapableBeanFactory.doCreateBean
        AService aService = (AService) beanFactory.doCreateBean("aService",
                rbd,null);
        System.out.println(aService);
        System.out.println(aService.id);

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

               if (bean instanceof AService){
                   return new AService$proxy((AService)bean);
               }
                return bean;
            }
        });
        //4、新增 单例池中
        //方式一
        beanFactory.addSingleton("aService",aService);
        //方式二
        beanFactory.getSingleton("aService",()->aService);
        System.out.println(aService);
    }

    /**
     * 4.DefaultSingletonBeanRegistry
     *      getSingleton
     *      singletonObjects
     *      singletonFactories
     *      earlySingletonObjects
     * 3.AbstractBeanFactory
     *      doGetBean
     * 2.AbstractAutowireCapableBeanFactory
     *      doCreateBean
     *      populateBean
     * 1.DefaultListableBeanFactory
     *     getBean
     */
    @Test
    public void getBeanTest(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(AService.class);
        rootBeanDefinition.setPropertyValues(new MutablePropertyValues().add("id","666"));
        beanFactory.registerBeanDefinition("aService",rootBeanDefinition);
        beanFactory.getBean(AService.class);
    }


    @Test
    public void getBeanTest2(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        {
            RootBeanDefinition mbd = new RootBeanDefinition(AService.class);
            //mbd.setScope("prototype"); 只有单例才能解决循环依赖
            mbd.setPropertyValues(new MutablePropertyValues().add("b",
                    new RuntimeBeanReference("bService")));
            beanFactory.registerBeanDefinition("aService",mbd);

        }
        {
            RootBeanDefinition mbd = new RootBeanDefinition(BService.class);
            //mbd.setScope("prototype");
            mbd.setPropertyValues(new MutablePropertyValues().add("a",
                    new RuntimeBeanReference("aService")));
            beanFactory.registerBeanDefinition("bService",mbd);

        }
        AService aService = beanFactory.getBean(AService.class);
        BService bService = beanFactory.getBean(BService.class);
    }

}
