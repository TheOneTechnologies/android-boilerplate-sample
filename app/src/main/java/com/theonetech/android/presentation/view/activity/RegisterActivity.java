package com.theonetech.android.presentation.view.activity;

import android.os.Bundle;
import android.view.View;

import com.theonetech.android.R;
import com.theonetech.android.databinding.ActivityRegisterBinding;
import com.theonetech.android.domain.utils.Utils;
import com.theonetech.android.domain.utils.ValidationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    private ActivityRegisterBinding binding = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                if (checkValidations(
                        Utils.getText(binding.edtName),
                        Utils.getText(binding.edtEmail),
                        Utils.getText(binding.edtPassword),
                        Utils.getText(binding.edtConfirmPassword))) {

                    setUserdata();
                }
                break;
            case R.id.btn_login:
                Utils.openActivity(this,LoginActivity.class,true);
                break;

            case R.id.image_back:
                finish();

                break;

            default:
                break;
        }

    }

    private void setUserdata() {

        Utils.openActivity(this,LoginActivity.class,true);

    }

    public boolean checkValidations(String username, String email, String password, String confirmPassword) {

        if (ValidationUtils.isBlank(username)) {
            Utils.showToast(this, getResources().getString(R.string.error_msg_empty_username));
            return false;

        } else if (!ValidationUtils.isValidEmail(email)) {
            Utils.showToast(this, getResources().getString(R.string.error_msg_empty_password));
            return false;

        } else if (ValidationUtils.isBlank(password)) {
            Utils.showToast(this, getResources().getString(R.string.error_msg_empty_password));
            return false;

        } else if (ValidationUtils.isBlank(confirmPassword)) {
            Utils.showToast(this, getString(R.string.error_msg_confirm_empty_password));
            return false;

        } else if (ValidationUtils.isMinLength(password, 6)) {
            Utils.showToast(this, getResources().getString(R.string.error_msg_invalid_password));
            return false;
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }
}
