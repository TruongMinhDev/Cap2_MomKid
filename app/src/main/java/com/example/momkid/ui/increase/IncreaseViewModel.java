package com.example.momkid.ui.increase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class IncreaseViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public IncreaseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Đây là trang thống kê");
    }

    public LiveData<String> getText() {
        return mText;
    }
}