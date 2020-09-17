package com.theonetech.android.presentation.view.login;

import android.view.View;

import com.theonetech.android.R;
import com.theonetech.android.domain.utils.Logger;
import com.theonetech.android.domain.utils.ValidationUtils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

/**
 * Created by bhoomika prajapati on 9/14/20.
 */
public class LoginViewModel extends ViewModel implements LifecycleObserver {

    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<Integer> errorMsg = new MutableLiveData<>();
    public MutableLiveData<Boolean> isValidate = new MutableLiveData<>();
    public MutableLiveData<Boolean> isOpenSignUpScreen = new MutableLiveData<>();


    public void onClick(View view) {

        if (view.getId() == R.id.btn_register) {

            isOpenSignUpScreen.setValue(true);

        } else {

            //Check Validation

            if (emailAddress.getValue() == null || emailAddress.getValue().isEmpty()) {
                errorMsg.setValue(R.string.error_msg_empty_username);


            } else if (password.getValue() == null || password.getValue().isEmpty()) {
                errorMsg.setValue(R.string.error_msg_empty_password);


            } else if (ValidationUtils.isMinLength(password.getValue(), 6)) {
                errorMsg.setValue(R.string.error_msg_invalid_password);

            } else {

                isValidate.setValue(true);
            }
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        Logger.e("Execute this method when Activity is resumed");
    }


}
