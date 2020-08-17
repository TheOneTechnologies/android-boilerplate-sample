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

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    private Context context;
    private List<AlbumsItem> albumsList;

    // Provide a suitable constructor (depends on the kind of list)
    public AlbumsAdapter(Context context, List<AlbumsItem> albumsList) {
        this.context = context;
        this.albumsList = albumsList;
    }

    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public AlbumsAdapter.MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        // create a new view
        ItemAlbumListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_album_list, parent, false);
        return new MyViewHolder(binding);


    }
    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.bind(albumsList.get(position));
    }

    // Return the size of your list (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return albumsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ItemAlbumListBinding listBinding;


        public MyViewHolder(ItemAlbumListBinding listBinding) {

            // each data item is just a model in this case
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
