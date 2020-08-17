package com.theonetech.android.domain.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.theonetech.android.R;



public class AlertDialogHelper {
         /**
          * Custom Alert dialog helper class
          *
          *  */


    public static void showAlertDialog(Context context, int icon, String title, String message, String positive, String negative, OnDialogClickListener onDialogClickListener, boolean isCancelable) {

        final Dialog dialog = new Dialog(context);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.app_dialog);
        ImageView ivDialogIcon = dialog.findViewById(R.id.ivDialogIcon);
        TextView tvDialogTitle = dialog.findViewById(R.id.tvDialogTitle);
        TextView tvDialogMessage = dialog.findViewById(R.id.tvDialogMessage);
        Button btnPositive = dialog.findViewById(R.id.btnPositive);
        Button btnNegative = dialog.findViewById(R.id.btnNegative);

        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;

        ivDialogIcon.setImageResource(icon);
        tvDialogTitle.setText(title);
        tvDialogMessage.setText(message);
        btnPositive.setText(positive);
        btnNegative.setText(negative);
        dialog.setCancelable(isCancelable);
        dialog.getWindow().setAttributes(params);
        dialog.show();

        btnPositive.setOnClickListener(v -> {

            onDialogClickListener.onButtonClick(v);
            dialog.dismiss();

        });

        btnNegative.setOnClickListener(v -> dialog.dismiss());

    }



    public interface OnDialogClickListener {
        void onButtonClick(View view);
    }
}
