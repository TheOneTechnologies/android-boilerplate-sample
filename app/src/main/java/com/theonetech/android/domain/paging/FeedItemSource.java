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
