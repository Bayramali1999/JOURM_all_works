package com.example.jourm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.jourm.R;

public class RegisterActivity extends AppCompatActivity {

    private ScrollView svPhone, svSms;
    private Button btnPhone, btnSms;
    private EditText etPhone, etSms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}