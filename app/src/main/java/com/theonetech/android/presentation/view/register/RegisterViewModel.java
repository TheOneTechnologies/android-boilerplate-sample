package com.theonetech.android.presentation.view.register;

import android.view.View;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by bhoomika prajapati on 9/17/20.
 */
public class RegisterViewModel extends ViewModel implements LifecycleObserver {

    public MutableLiveData<String> emailAddress = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> confirmPassword = new MutableLiveData<>();
    public MutableLiveData<ClickEvent> callBackMutableLiveData = new MutableLiveData<>();


    MutableLiveData<Integer> errorMsg = new MutableLiveData<>();
    MutableLiveData<Boolean> isValidate = new MutableLiveData<>();


    public MutableLiveData<ClickEvent> onClick() {
        return callBackMutableLiveData;
    }


    public void onClick(View view) {


    }

    public interface ClickEvent {
        public void onClicks(View view);
    }


}
