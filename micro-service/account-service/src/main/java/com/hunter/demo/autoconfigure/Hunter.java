package com.hunter.demo.autoconfigure;

public class Hunter {

    private HunterService hunterService;

    public HunterService getHunterService() {
        return hunterService;
    }

    public void setHunterService(HunterService hunterService) {
        this.hunterService = hunterService;
    }

    static Hunter build(){
        Hunter hunter = new Hunter();
        HunterService hunterService = new HunterService("build");
        hunter.setHunterService(hunterService);
        return hunter;
    }

    static Hunter build(HunterService hunterService){
        Hunter hunter = new Hunter();
        hunter.setHunterService(hunterService);
        return hunter;
    }
}
