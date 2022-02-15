package com.example.jourm.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jourm.R;
import com.example.jourm.widget.LanguageChangeFragment;

public class SettingActivity extends AppCompatActivity {

    private RelativeLayout lanRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        lanRL = findViewById(R.id.setting_lan);
        LanguageChangeFragment languageChangeFragment = new LanguageChangeFragment();
        lanRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageChangeFragment.show(getSupportFragmentManager(), "lang");
            }
        });
    }
}