package com.theonetech.android.presentation.view.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.theonetech.android.R;
import com.theonetech.android.databinding.ActivityRegisterBinding;
import com.theonetech.android.domain.utils.Logger;
import com.theonetech.android.domain.utils.Utils;
import com.theonetech.android.domain.utils.ValidationUtils;
import com.theonetech.android.presentation.view.login.LoginActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

public class RegisterActivity extends AppCompatActivity {


    private ActivityRegisterBinding binding = null;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        initialization();
    }

    private void initialization() {

        //The ViewModelProviders is responsible for creating, storing, and retrieving the view models.
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding.setRegisterViewModel(registerViewModel);

        getLifecycle().addObserver(registerViewModel);

    }








   /* public void onClick(View view) {

        Logger.e("Register...");

    *//*    switch (view.getId()) {
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
                Utils.startActivity(this, LoginActivity.class, true);
                break;

            case R.id.image_back:
                finish();

                break;

            default:
                break;
        }*//*

    }*/

    private void setUserdata() {

        Utils.startActivity(this, LoginActivity.class, true);

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
        super.onBackPressed();
        finish();
    }
}
