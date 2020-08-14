package com.theonetech.android.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theonetech.android.R;
import com.theonetech.android.data.connection.ApiInteractor;
import com.theonetech.android.data.connection.ApiResult;
import com.theonetech.android.databinding.FragmentListBinding;
import com.theonetech.android.domain.model.Albums;
import com.theonetech.android.domain.model.AlbumsItem;
import com.theonetech.android.domain.utils.Const;
import com.theonetech.android.presentation.adapter.AlbumsAdapter;
import com.theonetech.android.presentation.baseclass.BaseFragment;
import com.theonetech.android.presentation.view.activity.HomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by bhoomika prajapati on 8/5/20.
 */
public class ListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentListBinding binding;
    private AlbumsAdapter mAlbumAdapter = null;
    private List<AlbumsItem> mAlbumList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();

    }

    private void initialization() {
        setToolbar();
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvList.setHasFixedSize(true);
        mAlbumAdapter = new AlbumsAdapter(getContext(), mAlbumList);
        binding.rvList.setAdapter(mAlbumAdapter);

        binding.swipeToRefresh.setOnRefreshListener(this);

        callGetAlbumList();
    }


    private void setToolbar() {
        ((HomeActivity) Objects.requireNonNull(getActivity())).setToolbarTitle(getString(R.string.str_album_list));
    }

    private void callGetAlbumList() {
        HashMap<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(Const.PAGE_NUMBER, 1);
        paramsMap.put(Const.PAGE_SIZE, 20);

        ApiInteractor.getInstance(getContext(), true).getAlbumList(paramsMap, new ApiResult<Albums>() {
            @Override
            public void onSuccess(Albums response) {
                binding.swipeToRefresh.setRefreshing(false);
                mAlbumList.clear();
                mAlbumList.addAll(response.getItems());
                mAlbumAdapter.notifyDataSetChanged();


            }
        });
    }

    @Override
    public void onRefresh() {
        callGetAlbumList();
    }
}
