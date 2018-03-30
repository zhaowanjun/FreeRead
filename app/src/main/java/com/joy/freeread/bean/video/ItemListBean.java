package com.joy.freeread.bean.video;

/**
 * Created by admin on 2018/3/29.
 */
public class ItemListBean {

    private String type;
    private DataListBean dataList;
    private Object tag;
    private int id;
    private int adIndex;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataListBean getDataList() {
        return dataList;
    }

    public void setDataList(DataListBean data) {
        this.dataList = data;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdIndex() {
        return adIndex;
    }

    public void setAdIndex(int adIndex) {
        this.adIndex = adIndex;
    }

}
