package com.hunter.demo.autoconfigure;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HunterService {

    public HunterService(String clientAddress) {
       log.info("Hello ......."+clientAddress);
    }
}
