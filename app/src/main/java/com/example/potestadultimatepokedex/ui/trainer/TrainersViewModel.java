package com.example.potestadultimatepokedex.ui.trainer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrainersViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TrainersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}