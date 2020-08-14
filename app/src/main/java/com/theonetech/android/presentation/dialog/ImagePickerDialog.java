package com.theonetech.android.presentation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.theonetech.android.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * Created by bhoomika prajapati on 8/11/20.
 */
public class ImagePickerDialog extends Dialog {
    private Context mContext;
    private List<String> mList = new ArrayList<>();
    private TextView textCancel, textGallery, textCamera;
    private OnItemClickListener mOnItemClickListener;


    public ImagePickerDialog(@NonNull Context context, OnItemClickListener onItemClickListener) {
        super(context);
        this.mContext = context;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Rect displayRectangle = new Rect();
        int orientation = mContext.getResources().getConfiguration().orientation;
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setBackgroundDrawableResource(android.R.color.transparent);
        super.onCreate(savedInstanceState);
        setCancelable(true);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dialog_image_picker);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.getAttributes().windowAnimations = R.style.animation;

        initialization();
    }

    private void initialization() {
        textCamera = findViewById(R.id.text_photo_from_camera);
        textGallery = findViewById(R.id.text_photo_from_gallery);
        textCancel = findViewById(R.id.text_cancel);
        setListener();
    }

    private void setListener() {
        textCamera.setOnClickListener(v -> mOnItemClickListener.onCameraClick(v, this));
        textGallery.setOnClickListener(v -> mOnItemClickListener.onGalleryClick(v, this));
        textCancel.setOnClickListener(v -> dismiss());
    }


    public interface OnItemClickListener {

        void onCameraClick(View view, ImagePickerDialog dialog);

        void onGalleryClick(View view, ImagePickerDialog dialog);
    }
}
