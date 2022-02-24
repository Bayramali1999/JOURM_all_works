package com.example.jourm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jourm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.redmadrobot.inputmask.MaskedTextChangedListener;
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private ScrollView svPhone, svSms;
    private Button btnPhone, btnSms;
    private EditText etPhone, etSms;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    private String mVerificationId = "";
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String myPhone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ini();

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myPhone.length() == 13) {
                    sendPhoneNumber();
                    checkPhoneEt();
                } else {
                    etPhone.setBackgroundResource(R.drawable.input_error_style);
                }
            }
        });

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSms();
            }
        });
        setupPrefixPhone();
    }

    private void checkSms() {
        String text = etSms.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, text);
            signInWithPhoneAuthCredential(credential);
        }
    }

    private void checkPhoneEt() {
        btnPhone.setVisibility(View.GONE);
        svPhone.setVisibility(View.GONE);
        btnSms.setVisibility(View.VISIBLE);
        svSms.setVisibility(View.VISIBLE);
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
                Toast.makeText(RegisterActivity.this, "onVerificationCompleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(RegisterActivity.this, "onVerificationFailed", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onVerificationFailed: " + e.getMessage());
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                mVerificationId = verificationId;
                mResendToken = token;
                Toast.makeText(RegisterActivity.this, "onCodeSent", Toast.LENGTH_SHORT).show();
            }
        };
    }


    private void sendPhoneNumber() {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(myPhone)
                        .setTimeout(30L, TimeUnit.SECONDS)
                        .setActivity(RegisterActivity.this)
                        .setCallbacks(mCallback)
                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);


    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            FirebaseUser user = task.getResult().getUser();
                            gotoMainActivity();
                        } else {
                            Toast.makeText(RegisterActivity.this,
                                    "signInWithPhoneAuthCredential",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainViewActivity.class);
        startActivity(intent);
        finish();

    }

    private void setupPrefixPhone() {
        final List<String> affineFormats = new ArrayList<>();
        affineFormats.add("+998 ([00]) [000]-[00]-[00]");

        final MaskedTextChangedListener listener = MaskedTextChangedListener.Companion.installOn(
                etPhone,
                "+998 ([00]) [000]-[00]-[00]",
                affineFormats,
                AffinityCalculationStrategy.PREFIX,
                new MaskedTextChangedListener.ValueListener() {
                    @Override
                    public void onTextChanged(boolean maskFilled, @NonNull final String extractedValue, @NonNull String formattedText) {
                        myPhone = "+998" + extractedValue;
                    }
                }
        );
        etPhone.setHint(listener.placeholder());
    }


}