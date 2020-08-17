package com.theonetech.android.presentation.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

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
import com.theonetech.android.domain.utils.ValidationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding binding = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initialization();

    }

    private void initialization() {
        setListener();
    }

    private void setListener() {
        binding.btnLogin.setOnClickListener(this);
        binding.btnRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_login:
                if (checkValidations(
                        Utils.getText(binding.edtUsername),
                        Utils.getText(binding.edtPassword))) {

                    callLoginApi();
                }

                break;

            case R.id.btn_register:
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                break;

            default:
                break;

        }

    }
   //Check Validation
    protected boolean checkValidations(String username, String password) {

        if (username.isEmpty()) {
            Utils.showToast(this, getResources().getString(R.string.error_msg_empty_username));
            return false;
        } else if (password.isEmpty()) {
            Utils.showToast(this, getResources().getString(R.string.error_msg_empty_password));
            return false;
        } else if (ValidationUtils.isMinLength(password, 6)) {
            Utils.showToast(this, getResources().getString(R.string.error_msg_invalid_password));
            return false;
        }

        return true;
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


                    Utils.openActivity(LoginActivity.this, HomeActivity.class, true);

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
