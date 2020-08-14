package com.theonetech.android.domain.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.theonetech.android.R;

public class ProgressDialogUtils {
    public static ProgressDialogUtils progressDialogUtils;

    public static ProgressDialogUtils getInstance() {
        if (progressDialogUtils == null) {
            progressDialogUtils = new ProgressDialogUtils();
        }
        return progressDialogUtils;
    }

    /*
     * Create an progress dialog instance
     * @param Context context
     * @Title title of String
     *
     * */


    public static ProgressDialog showProgressDialog(Context context) {
        final ProgressDialog progressDialog = new ProgressDialog(context, R.style.AppTheme_ProgressDialog_Theme);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        return progressDialog;
    }

    public static ProgressDialog showProgressDialog(Context context, String msg) {
        final ProgressDialog progressDialog = new ProgressDialog(context, R.style.AppTheme_ProgressDialog_Theme);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(msg);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        return progressDialog;
    }


    /*
     * It dismiss progress dialog if already visible
     *
     * */
    public void dismissProgress(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
