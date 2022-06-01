package com.example.doccare.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TokenViewModel extends ViewModel {
    private final static MutableLiveData<String> accesstoken = new MutableLiveData<>();

    public final void setAccesstoken(String acctoken) {
        accesstoken.setValue(acctoken);
    }
    public final LiveData<String> getAT() {
        return accesstoken;
    }
    public final String getAccessToken(){
        return accesstoken.getValue();
    }

}
