package com.imswy.slideviewdemo.gridview;

public class GridDataBean {

    private String Index;

    public String getIndex () {
        return Index;
    }

    public void setIndex (String index) {
        Index = index;
    }

    public GridDataBean (String index) {
        Index = index;
    }

    public GridDataBean () {
    }

    @Override
    public String toString () {
        return "RecyclerDataBean{" +
                "Index='" + Index + '\'' +
                '}';
    }
}
