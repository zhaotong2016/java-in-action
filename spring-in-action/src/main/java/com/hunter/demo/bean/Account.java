package com.hunter.demo.bean;

//@Component
public class Account {


    private int id;

    private String name;

    private int age;

    public void test(){
        System.out.println("Test");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}