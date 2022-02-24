package com.example.jourm.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class CalendarPageAdapter extends FragmentPagerAdapter {

    private ArrayList<String> stringArrayList = new ArrayList<>();
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public CalendarPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public CalendarPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addFragment(Fragment fragment, String s) {
        stringArrayList.add(s);
        fragmentArrayList.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrayList.get(position);
    }
}
