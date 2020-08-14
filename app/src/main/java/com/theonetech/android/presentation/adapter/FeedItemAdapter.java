package com.theonetech.android.presentation.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.theonetech.android.R;
import com.theonetech.android.databinding.ItemFeedListBinding;
import com.theonetech.android.domain.model.Works;
import com.theonetech.android.domain.utils.DateUtils;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


public class FeedItemAdapter extends PagedListAdapter<Works, FeedItemAdapter.ItemViewHolder> {

    private static DiffUtil.ItemCallback<Works> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Works>() {
                @Override
                public boolean areItemsTheSame(Works oldItem, Works newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Works oldItem, Works newItem) {
                    return oldItem.equals(newItem);
                }
            };


    public FeedItemAdapter() {
        super(DIFF_CALLBACK);

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_feed_list, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(Objects.requireNonNull(getItem(position)));
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemFeedListBinding feedItemBinding;

        public ItemViewHolder(ItemFeedListBinding feedItemBinding) {
            super(feedItemBinding.getRoot());
            this.feedItemBinding = feedItemBinding;
        }

        public void bind(Works work) {
            feedItemBinding.tvDay.setText(DateUtils.getDayFromString(work.getDate()));
            feedItemBinding.tvMonthYear.setText(DateUtils.parseDateToddMMYY(work.getDate()));
            feedItemBinding.tvSubjectName.setText(work.getSubjectName());
            feedItemBinding.tvDescription.setText(work.getDescription());
        }
    }
}
