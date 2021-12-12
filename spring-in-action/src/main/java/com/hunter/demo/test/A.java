package com.hunter.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hunter
 * @date 2021/12/09 10:15
 **/
@Service
public class A {

    @Autowired
    private B b;
}
