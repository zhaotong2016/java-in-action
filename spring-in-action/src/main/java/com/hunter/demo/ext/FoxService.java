package com.hunter.demo.ext;

public class FoxService {


    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FoxService{" +
                "name='" + name + '\'' +
                '}';
    }

    public FoxService() {
        System.out.println("init foxService。。。。。。。。。。。");
    }
}
