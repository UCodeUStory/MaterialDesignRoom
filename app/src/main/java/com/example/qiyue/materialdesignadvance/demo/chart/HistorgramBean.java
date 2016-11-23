package com.example.qiyue.materialdesignadvance.demo.chart;

/**
 * Created by qiyue on 2016/10/25.
 */
public class HistorgramBean {

    private int color;

    public HistorgramBean(int color, float height) {
        this.color = color;
        this.height = height;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    private float height;

}
