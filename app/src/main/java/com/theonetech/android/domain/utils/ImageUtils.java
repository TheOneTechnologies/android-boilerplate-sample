package com.theonetech.android.domain.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.theonetech.android.R;

/**
 * Created by bhoomika prajapati on 8/5/20.
 */
public class ImageUtils {

    /**
     * displayImage with common place holder
     **/

    public static void displayImage(Context context, String resource, ImageView imageView) {
        Glide.with(context)
                .load(resource)
                .placeholder(R.drawable.ic_logo)
                .error(R.drawable.ic_logo)
                .into(imageView);
    }

    public static void displayImage(Context context, String resource, ImageView imageView, int placeholder) {
        Glide.with(context)
                .load(resource)
                .placeholder(placeholder)
                .error(placeholder)
                .into(imageView);
    }

    public static void displayCircleImage(Context context, String resource, final ImageView imageView) {
        Glide.with(context).load(resource).circleCrop()
                .load(resource)
                .placeholder(R.drawable.ic_logo)
                .error(R.drawable.ic_logo)
                .into(imageView);
    }

    public static void displayCircleImage(Context context, String resource, final ImageView imageView, int placeholder) {
        Glide.with(context).load(resource).circleCrop()
                .load(resource)
                .placeholder(placeholder)
                .error(placeholder)
                .into(imageView);
    }
}
