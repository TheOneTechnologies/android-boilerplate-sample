package com.theonetech.android.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeWorks {

    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;

    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;

    @SerializedName("total")
    @Expose
    private Integer total;


    @SerializedName("items")
    @Expose
    private List<com.theonetech.android.domain.model.Works> items = null;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<com.theonetech.android.domain.model.Works> getItems() {
        return items;
    }

    public void setItems(List<com.theonetech.android.domain.model.Works> items) {
        this.items = items;
    }

}
