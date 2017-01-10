package com.example.qiyue.materialdesignadvance.demo2.diffutil;

/**
 * Created by qiyue on 2016/12/26.
 */

public class Person implements Cloneable {

    /**
     * id 用来表示唯一一个人，而这个人的其他字段是可以修改的，id不可以修改
     */
    private String id;
    private String name;
    private String age;

    public Person(String id, String name, String age){
        this.id = id;
        this.name = name;
        this.age = age;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    //仅写DEMO 用 实现克隆方法
    @Override
    public Person clone() throws CloneNotSupportedException {
        Person bean = null;
        try {
            bean = (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
