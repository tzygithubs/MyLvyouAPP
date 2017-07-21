package com.dlxy.domain;

/**
 * Created by T on 2017/7/14.
 */

public class ShopCart {
    private String name ;
    private int sum;
    private String jieshao;
    private String kaishi;
    private String zhongdian;

    public String getKaishi() {
        return kaishi;
    }

    public void setKaishi(String kaishi) {
        this.kaishi = kaishi;
    }

    public String getZhongdian() {
        return zhongdian;
    }

    public void setZhongdian(String zhongdian) {
        this.zhongdian = zhongdian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getJieshao() {
        return jieshao;
    }

    public void setJieshao(String jieshao) {
        this.jieshao = jieshao;
    }
}
