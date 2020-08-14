package com.theonetech.android.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Albums {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("items")
    @Expose
    private List<com.theonetech.android.domain.model.AlbumsItem> items = null;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    public List<com.theonetech.android.domain.model.AlbumsItem> getItems() {
        return items;
    }



}
