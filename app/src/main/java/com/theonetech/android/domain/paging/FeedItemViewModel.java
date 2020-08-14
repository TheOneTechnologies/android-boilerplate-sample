package com.theonetech.android.domain.paging;


import android.content.Context;

import com.theonetech.android.domain.model.Works;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class FeedItemViewModel extends ViewModel {

    public LiveData<PagedList<Works>> itemPagedList;
    public LiveData<PageKeyedDataSource<Integer, Works>> liveDataSource;
    public int PAGE_SIZE = 50;


    public FeedItemViewModel() {

        FeedSourceFactory itemDataSourceFactory = new FeedSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(PAGE_SIZE).build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)).build();

    }


}
