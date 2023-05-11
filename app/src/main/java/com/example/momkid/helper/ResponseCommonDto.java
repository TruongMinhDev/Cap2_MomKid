package com.example.momkid.helper;


import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ResponseCommonDto<T> {
    private List<T> data;

    private int count;
    private int total;
    private int page;
    private int pageCount;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    protected ResponseCommonDto<T> doInBackground(Class<T> type, String json, Void... params) {
        GsonBuilder gson = new GsonBuilder();
        Type collectionType = new TypeToken<ResponseCommonDto<T>>(){}.getType();
        ResponseCommonDto<T> myJson = gson.create().fromJson(json, collectionType);
        return myJson;
    }
}
