package com.theonetech.android.presentation.view.activity;

import android.os.Bundle;
import android.os.Handler;

import com.theonetech.android.R;
import com.theonetech.android.databinding.ActivitySplashBinding;
import com.theonetech.android.domain.utils.Const;
import com.theonetech.android.domain.utils.SharedPrefUtils;
import com.theonetech.android.domain.utils.Utils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);


        new Handler().postDelayed(() -> {

            if (SharedPrefUtils.getBooleanData(SplashActivity.this, Const.IS_LOGGED_IN)) {
                Utils.openActivity(this, HomeActivity.class, true);

            } else {
                Utils.openActivity(this, LoginActivity.class, true);

            }
        }, 2000);
    }
}
