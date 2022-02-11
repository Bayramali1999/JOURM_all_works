package com.example.jourm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jourm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private ScrollView svPhone, svSms;
    private Button btnPhone, btnSms;
    private EditText etPhone, etSms;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ini();
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendPhoneNumber();
                checkPhoneEt();
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSms();
            }
        });
    }

    private void checkSms() {
        String text = etSms.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, text);
            signInWithPhoneAuthCredential(credential);
        }
    }

    private void checkPhoneEt() {
        if (true) {

            btnPhone.setVisibility(View.GONE);
            svPhone.setVisibility(View.GONE);
            btnSms.setVisibility(View.VISIBLE);
            svSms.setVisibility(View.VISIBLE);
        }
    }

    private void ini() {
        mAuth = FirebaseAuth.getInstance();
        svPhone = findViewById(R.id.phone_view);
        svSms = findViewById(R.id.sms_code_view);
        btnPhone = findViewById(R.id.btn_continue_phone);
        btnSms = findViewById(R.id.btn_continue_sms);
        etPhone = findViewById(R.id.et_phone_number);
        etSms = findViewById(R.id.et_sms_code);

        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }


    private void sendPhoneNumber() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+998998827479")       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = task.getResult().getUser();
                            gotoMainActivity();
                        } else {
                        }
                    }
                });
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainViewActivity.class);
        startActivity(intent);
        finish();

    }

}