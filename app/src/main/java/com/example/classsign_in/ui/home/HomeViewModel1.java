package com.example.classsign_in.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel1 extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel1() {
        Log.d("TEST_DEBUG","HomeViewModel1");
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }

}