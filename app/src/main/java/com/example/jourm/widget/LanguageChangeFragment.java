package com.example.jourm.widget;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jourm.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class LanguageChangeFragment extends BottomSheetDialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_language_change, container, false);
    }
}