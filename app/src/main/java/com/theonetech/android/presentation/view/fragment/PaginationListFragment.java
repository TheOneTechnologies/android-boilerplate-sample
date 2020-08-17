package com.theonetech.android.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theonetech.android.R;
import com.theonetech.android.databinding.FragmentPaginationListBinding;
import com.theonetech.android.domain.paging.FeedItemViewModel;
import com.theonetech.android.presentation.adapter.FeedItemAdapter;
import com.theonetech.android.presentation.baseclass.BaseFragment;
import com.theonetech.android.presentation.view.activity.HomeActivity;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * Created by bhoomika prajapati on 8/5/20.
 */
public class PaginationListFragment extends BaseFragment {

    private FragmentPaginationListBinding binding;
    private FeedItemAdapter mFeedAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pagination_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization();
    }

    private void initialization() {
        setToolbar();
        // use a linear layout manager
        binding.rvList.setLayoutManager(new LinearLayoutManager(getContext()));

        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        binding.rvList.setHasFixedSize(true);

        FeedItemViewModel itemViewModel = new ViewModelProvider(this).get(FeedItemViewModel.class);
        // define an adapter
        mFeedAdapter = new FeedItemAdapter();
        // observe data
        itemViewModel.itemPagedList.observe(getViewLifecycleOwner(), items -> {
            binding.rvList.setVisibility(View.VISIBLE);
            mFeedAdapter.submitList(items);

        });

        binding.rvList.setAdapter(mFeedAdapter);

    }

    private void setToolbar() {
        ((HomeActivity) Objects.requireNonNull(getActivity())).setToolbarTitle(getString(R.string.str_list_pagination));
    }
}
