package com.imswy.slideviewdemo.recyclerview;

public class RecyclerDataBean {

    private String Index;

    public String getIndex () {
        return Index;
    }

    public void setIndex (String index) {
        Index = index;
    }

    public RecyclerDataBean (String index) {
        Index = index;
    }

    public RecyclerDataBean () {
    }

    @Override
    public String toString () {
        return "RecyclerDataBean{" +
                "Index='" + Index + '\'' +
                '}';
    }
}
