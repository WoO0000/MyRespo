package com.example.classsign_in.ui.dashboard;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel1 extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardViewModel1() {
        Log.d("TEST_DEBUG","DashboardViewModel1");
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}