package com.theonetech.android.domain.paging;


import android.content.Context;

import com.theonetech.android.data.connection.ApiInteractor;
import com.theonetech.android.data.connection.ApiResult;
import com.theonetech.android.domain.application.GlobalApplication;
import com.theonetech.android.domain.model.HomeWorks;
import com.theonetech.android.domain.model.Works;
import com.theonetech.android.domain.utils.Const;
import com.theonetech.android.domain.utils.SharedPrefUtils;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;


public class FeedItemSource extends PageKeyedDataSource<Integer, Works> {

    private final String token;
    private Context mContext;
    public int FIRST_PAGE = 1;

    FeedItemSource() {
        mContext = GlobalApplication.getAppContext();
        token = "Bearer " + SharedPrefUtils.getStringData(mContext, Const.ACCESS_TOKEN);
    }


    /*
    * This method is called first to initialize a PagedList with data. If it's possible to count the items that can be loaded by the DataSource,
    * This enables PagedLists presenting data from this source to display placeholders to represent unloaded items.
    * */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Works> callback) {

        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(Const.PAGE_NUMBER, FIRST_PAGE);
        paramsMap.put(Const.PAGE_SIZE, 10);

        ApiInteractor.getInstance(mContext).getHomeWorkList(paramsMap, new ApiResult<HomeWorks>() {
            @Override
            public void onSuccess(HomeWorks response) {
                callback.onResult(response.getItems(), null, FIRST_PAGE + 1);
            }
        });
    }


    /*
    * It's valid to return a different list size than the page size if it's easier,
    * e.g. Data may be passed synchronously during the load method, or deferred and called at a later time.
    * Further loads going down will be blocked until the callback is called.
    * */
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Works> callback) {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(Const.PAGE_NUMBER, params.key);
        paramsMap.put(Const.PAGE_SIZE, 10);

        ApiInteractor.getInstance(mContext).getHomeWorkList(paramsMap, new ApiResult<HomeWorks>() {
            @Override
            public void onSuccess(HomeWorks response) {
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                callback.onResult(response.getItems(), adjacentKey);
            }
        });
    }

    /*
    * It's valid to return a different list size than the page size if it's easier,
    * e.g. is called in the subsequent fetches, mostly after the user scrolls and reaches the end of the list
    * */
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Works> callback) {

        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(Const.PAGE_NUMBER, params.key);
        paramsMap.put(Const.PAGE_SIZE, 10);

        ApiInteractor.getInstance(mContext).getHomeWorkList(paramsMap, new ApiResult<HomeWorks>() {
            @Override
            public void onSuccess(HomeWorks response) {
                callback.onResult(response.getItems(), params.key + 1);
            }
        });
    }
}
