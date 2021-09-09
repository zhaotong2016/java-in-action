package com.hunter.demo.autoconfigure;

import org.springframework.util.Assert;

public class HunterHealthIndicator {

    private Hunter hunter;

    public HunterHealthIndicator(Hunter hunter) {
        Assert.notNull(hunter, "Web3j must not be null");
        this.hunter = hunter;
    }
}
