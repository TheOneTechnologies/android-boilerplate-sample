package com.theonetech.android.presentation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.theonetech.android.R;
import com.theonetech.android.databinding.ItemAlbumListBinding;
import com.theonetech.android.domain.model.AlbumsItem;
import com.theonetech.android.domain.utils.DateUtils;
import com.theonetech.android.domain.utils.ImageUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by bhoomika prajapati on 8/12/20.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    private Context context;
    private List<AlbumsItem> albumsList;


    public AlbumsAdapter(Context context, List<AlbumsItem> albumsList) {
        this.context = context;
        this.albumsList = albumsList;
    }

    @NotNull
    @Override
    public AlbumsAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemAlbumListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_album_list, parent, false);
        return new MyViewHolder(binding);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.bind(albumsList.get(position));
    }


    @Override
    public int getItemCount() {
        return albumsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ItemAlbumListBinding listBinding;


        public MyViewHolder(ItemAlbumListBinding listBinding) {
            super(listBinding.getRoot());
            this.listBinding = listBinding;
        }

        public void bind(AlbumsItem item) {
            listBinding.textName.setText(item.getName());
            listBinding.textDate.setText(DateUtils.getDayFromString(item.getDate()) + " " + DateUtils.parseDateToddMMYY(item.getDate()));
            ImageUtils.displayCircleImage(context, item.getPhotoUrl(), listBinding.imgAlbum);
        }
    }
}
