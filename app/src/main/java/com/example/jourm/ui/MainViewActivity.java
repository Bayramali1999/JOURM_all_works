package com.example.jourm.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.jourm.R;
import com.example.jourm.ui.main_view.CalendarFragment;
import com.example.jourm.ui.main_view.GradeFragment;
import com.example.jourm.ui.main_view.HomeFragment;
import com.example.jourm.ui.main_view.MarkFragment;
import com.example.jourm.ui.main_view.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainViewActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private HomeFragment mainFragment;
    private GradeFragment gradeFragment;
    private ProfileFragment profileFragment;
    private CalendarFragment scheduleFragment;
    private MarkFragment visitFragment;

    private int selectedState = 1;

    private LinearLayout homeLayout, calendarLayout, gradeLayout, markLayout, profileLayout;
    private ImageView homeImage, calendarImage, gradeImage, markImage, profileImage;
    private TextView homeTV, calendarTV, gradeTV, markTV, profileTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        init();
        setChangeFragment(mainFragment);
        navItemClick();
    }


    public void init() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        mainFragment = new HomeFragment();
        gradeFragment = new GradeFragment();
        profileFragment = new ProfileFragment();
        scheduleFragment = new CalendarFragment();
        visitFragment = new MarkFragment();

        homeLayout = findViewById(R.id.nav_home_container);
        calendarLayout = findViewById(R.id.nav_calendar_container);
        gradeLayout = findViewById(R.id.nav_grade_container);
        markLayout = findViewById(R.id.nav_mark_container);
        profileLayout = findViewById(R.id.nav_profile_container);

        homeImage = findViewById(R.id.nav_home);
        calendarImage = findViewById(R.id.nav_calendar);
        gradeImage = findViewById(R.id.nav_grade);
        markImage = findViewById(R.id.nav_mark);
        profileImage = findViewById(R.id.nav_profile);

        homeTV = findViewById(R.id.tv_home);
        calendarTV = findViewById(R.id.tv_calendar);
        gradeTV = findViewById(R.id.tv_grade);
        markTV = findViewById(R.id.tv_mark);
        profileTV = findViewById(R.id.tv_profile);

    }


    private void setChangeFragment(Fragment fm) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("back_state")
                .replace(R.id.main_view_container, fm)
                .commit();
    }

    private void navItemClick() {
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChangeFragment(mainFragment);
                backStyle();
                changeStyle(1);
            }
        });
        calendarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChangeFragment(scheduleFragment);
                backStyle();
                changeStyle(2);
            }
        });
        gradeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChangeFragment(gradeFragment);
                backStyle();
                changeStyle(3);
            }
        });
        markLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChangeFragment(visitFragment);
                backStyle();
                changeStyle(4);
            }
        });
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChangeFragment(profileFragment);
                backStyle();
                changeStyle(5);
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void backStyle() {
        homeLayout.setBackgroundResource(R.drawable.default_backgraund_style);
        calendarLayout.setBackgroundResource(R.drawable.default_backgraund_style);
        markLayout.setBackgroundResource(R.drawable.default_backgraund_style);
        gradeLayout.setBackgroundResource(R.drawable.default_backgraund_style);
        profileLayout.setBackgroundResource(R.drawable.default_backgraund_style);

        homeImage.setImageResource(R.drawable.home_selected);
        calendarImage.setImageResource(R.drawable.calendar);
        markImage.setImageResource(R.drawable.mark);
        gradeImage.setImageResource(R.drawable.grade);
        profileImage.setImageResource(R.drawable.ic_baseline_person_24);

        homeTV.setTextColor(R.color.hint_color);
        calendarTV.setTextColor(R.color.hint_color);
        markTV.setTextColor(R.color.hint_color);
        gradeTV.setTextColor(R.color.hint_color);
        profileTV.setTextColor(R.color.hint_color);
    }

    @SuppressLint("ResourceAsColor")
    private void changeStyle(int i) {
        switch (i) {
            case 1:
                //home
                homeLayout.setBackgroundResource(R.drawable.nav_border_style);
                homeImage.setImageResource(R.drawable.home_selected);
                homeTV.setTextColor(R.color.main_color);
                break;
            case 2:
                //calendar
                calendarLayout.setBackgroundResource(R.drawable.nav_border_style);
//                calendarImage.setImageResource(R.drawable.calendar_select);
                calendarTV.setTextColor(R.color.main_color);
                break;
            case 3:
                //grade
                gradeLayout.setBackgroundResource(R.drawable.nav_border_style);
//                gradeImage.setImageResource(R.drawable.grade_select);
                gradeTV.setTextColor(R.color.main_color);
                break;
            case 4:
                //mark
                markLayout.setBackgroundResource(R.drawable.nav_border_style);
//                markImage.setImageResource(R.drawable.mark_select);
                markTV.setTextColor(R.color.main_color);
                break;
            case 5:
                //profile
                profileLayout.setBackgroundResource(R.drawable.nav_border_style);
//                profileImage.setImageResource(R.drawable.person_select);
                profileTV.setTextColor(R.color.main_color);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (currentUser == null) {
            gotoLoginActivity();
        }
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}