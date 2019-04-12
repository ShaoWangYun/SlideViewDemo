package com.imswy.slideviewdemo.listview;

public class ListDataBean {

    private String Index;

    public String getIndex () {
        return Index;
    }

    public void setIndex (String index) {
        Index = index;
    }

    public ListDataBean (String index) {
        Index = index;
    }

    public ListDataBean () {
    }

    @Override
    public String toString () {
        return "RecyclerDataBean{" +
                "Index='" + Index + '\'' +
                '}';
    }
}
