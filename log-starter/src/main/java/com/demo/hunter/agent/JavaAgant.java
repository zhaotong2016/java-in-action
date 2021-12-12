package com.demo.hunter.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @author Hunter
 * @date 2021/12/07 22:29
 **/
public class JavaAgant {

    public static void preMain(String agentArgs,Instrumentation inst){
        System.out.println("this is an agent.");
        System.out.println("this is an agent 启动了");
        System.out.println("args:" + agentArgs + "\n");
        Class[] allLoadedClasses = inst.getAllLoadedClasses();
        for (Class loadedClass : allLoadedClasses) {
            if (loadedClass.getClassLoader()!=null){
                System.out.println("classLoader" + loadedClass.getClassLoader().toString());
            }else {
                System.out.println("\n");
            }
        }
    }
}
