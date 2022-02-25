package com.example.jourm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jourm.R;
import com.example.jourm.widget.LanguageChangeFragment;
import com.google.firebase.auth.FirebaseAuth;

public class SettingActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private RelativeLayout lanRL, outRL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ini();

        LanguageChangeFragment languageChangeFragment = new LanguageChangeFragment();

        lanRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageChangeFragment.show(getSupportFragmentManager(), "lang");
            }
        });

        outRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(SettingActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ini() {
        mAuth = FirebaseAuth.getInstance();
        lanRL = findViewById(R.id.setting_lan);
        outRL = findViewById(R.id.setting_out);
    }
}