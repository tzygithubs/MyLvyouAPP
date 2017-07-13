package com.dlxy.domain;

/**
 * Created by T on 2017/7/12.
 */

public class Customer {
    private String name;
    private int id;
    private String password;
    private String gender;
    private String avator;
    private String age;

    public Customer() {
    }

    public Customer(String name, int id, String password, String gender, String avator, String age) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.gender = gender;
        this.avator = avator;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", avator='" + avator + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
