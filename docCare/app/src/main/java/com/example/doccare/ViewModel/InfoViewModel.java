package com.example.doccare.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doccare.Activity.LoginActivity;
import com.example.doccare.Model.Account;
import com.example.doccare.Model.LoginResponse;

public class InfoViewModel extends ViewModel {
    private final static MutableLiveData<LoginResponse> loginResponse  = new MutableLiveData<>();


    public final void setLiveInfo(LoginResponse m_loginResponse) {
        loginResponse.setValue(m_loginResponse);
    }
    public final LiveData<LoginResponse> getLiveInfo() {
        return loginResponse;
    }

    public final LoginResponse getLoginResponse(){
        return loginResponse.getValue();
    }
}
