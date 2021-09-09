package com.hunter.demo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyDemo {
    public static void main1(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // $Proxy0
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
                    return echoService.echo((String) args[0]);
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello,World");

        // $Proxy1
        Object proxy2 = Proxy.newProxyInstance(classLoader,
                new Class[]{Comparable.class},
                (proxy1, method, args1) -> {
                    return null;
                });

        System.out.println(proxy2);
    }

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("===>\n" + classLoader);

        ClassLoader parentClassLoader = classLoader;
        while (true) {
            parentClassLoader = parentClassLoader.getParent();
            if (parentClassLoader == null) { // Bootstrap ClassLoader
                break;
            }
            System.out.println(parentClassLoader);
        }
    }

}
