package com.theonetech.android.domain.paging;

import com.theonetech.android.domain.model.Works;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class FeedSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Works>> itemLiveDataSource = new MutableLiveData<>();


    /**
    * The DataSource should invalidate itself if the snapshot is no longer valid.
    * If a DataSource becomes invalid, the only way to query more data is to create a new DataSource from the Factory.
    * */
    @NotNull
    @Override
    public DataSource<Integer, Works> create() {
        FeedItemSource itemDataSource = new FeedItemSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    /**
    * Incremental data loader for page-keyed content, where requests return keys for next/previous pages.
    * e.g Implement a DataSource using PageKeyedDataSource if you need to use data from page N - 1 to load page N.
    */
    public MutableLiveData<PageKeyedDataSource<Integer, Works>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
