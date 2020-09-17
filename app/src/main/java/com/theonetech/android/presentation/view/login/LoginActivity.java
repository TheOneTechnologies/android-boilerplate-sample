package com.theonetech.android.presentation.view.login;

import android.os.Build;
import android.os.Bundle;

import com.google.gson.JsonObject;
import com.theonetech.android.R;
import com.theonetech.android.data.connection.ApiInteractor;
import com.theonetech.android.data.connection.ApiResult;
import com.theonetech.android.databinding.ActivityLoginBinding;
import com.theonetech.android.domain.model.LoginResponse;
import com.theonetech.android.domain.utils.Const;
import com.theonetech.android.domain.utils.NotificationUtils;
import com.theonetech.android.domain.utils.SharedPrefUtils;
import com.theonetech.android.domain.utils.Utils;
import com.theonetech.android.presentation.view.activity.HomeActivity;
import com.theonetech.android.presentation.view.register.RegisterActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity {


    LoginViewModel loginViewModel;

    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        //The ViewModelProviders is responsible for creating, storing, and retrieving the view models.
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.setLoginViewModel(loginViewModel);

        /**
         *  holding this state of the lifecycle, objects can observe this state and react accordingly.
         *  In order to keep a track of this state
         * */

        getLifecycle().addObserver(loginViewModel);


        initialization();

    }

    private void initialization() {


        loginViewModel.errorMsg.observe(this, integer -> {
            Utils.showToast(LoginActivity.this, getResources().getString(integer));
        });

        loginViewModel.isValidate.observe(this, aBoolean -> callLoginApi());

        loginViewModel.isOpenSignUpScreen.observe(this, aBoolean -> Utils.startActivity(LoginActivity.this, RegisterActivity.class, true));


    }


    private void callLoginApi() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Const.USERNAME, Utils.getText(binding.edtUsername));
        jsonObject.addProperty(Const.PASSWORD, Utils.getText(binding.edtPassword));
        jsonObject.addProperty(Const.SCHOOL_ID, 1);
        jsonObject.addProperty(Const.IS_STUDENT, true);

        ApiInteractor.getInstance(this, true).loginUser(jsonObject, new ApiResult<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                try {
                    saveData(response);

                    NotificationUtils notificationUtils = new NotificationUtils();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        notificationUtils.sendNotification(LoginActivity.this);
                    }


                    Utils.startActivity(LoginActivity.this, HomeActivity.class, true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });

    }

    private void saveData(LoginResponse response) {
        SharedPrefUtils.saveData(LoginActivity.this, Const.ACCESS_TOKEN, response.getAccessToken());
        SharedPrefUtils.saveData(LoginActivity.this, Const.TOKEN_TYPE, response.getTokenType());
        SharedPrefUtils.saveData(LoginActivity.this, Const.IS_LOGGED_IN, true);
    }
}
