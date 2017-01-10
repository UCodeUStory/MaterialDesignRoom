package com.example.qiyue.materialdesignadvance.demo2.dbexecutor;

import com.shizhefei.db.annotations.Id;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class Person {

    @Id
    String id;

    String name;

    int age;

    String createTime;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", age=" + age +
                '}';
    }

    public Person(String name, String id, int age, String time) {
        super();
        this.name = name;
        this.id = id;
        this.age = age;
        this.createTime = time;
    }

    public Person() {
        super();
    }
}