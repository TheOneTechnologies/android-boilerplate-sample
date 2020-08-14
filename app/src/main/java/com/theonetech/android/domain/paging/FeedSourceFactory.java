package com.theonetech.android.domain.paging;


import android.content.Context;

import com.theonetech.android.domain.model.Works;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class FeedSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Works>> itemLiveDataSource = new MutableLiveData<>();
    private Context mContext;



    @NotNull
    @Override
    public DataSource<Integer, Works> create() {
        FeedItemSource itemDataSource = new FeedItemSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Works>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
