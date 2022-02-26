package com.example.jourm.ui.main_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.jourm.R;
import com.example.jourm.ui.main_view.calendar_view.FridayFragment;
import com.example.jourm.ui.main_view.calendar_view.MondayFragment;
import com.example.jourm.ui.main_view.calendar_view.SaturdayFragment;
import com.example.jourm.ui.main_view.calendar_view.SundayFragment;
import com.example.jourm.ui.main_view.calendar_view.ThursdayFragment;
import com.example.jourm.ui.main_view.calendar_view.TuesdayFragment;
import com.example.jourm.ui.main_view.calendar_view.WednesdayFragment;
import com.example.jourm.widget.CalendarPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class CalendarFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private CalendarPageAdapter pagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);

        viewPager = v.findViewById(R.id.calendar_vp);
        tabLayout = v.findViewById(R.id.calendar_tl);
        pagerAdapter = new CalendarPageAdapter(getActivity().getSupportFragmentManager());
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pagerAdapter.addFragment(new MondayFragment(), "Dushanba");
        pagerAdapter.addFragment(new TuesdayFragment(), "Seshanba");
        pagerAdapter.addFragment(new WednesdayFragment(), "Chorshanba");
        pagerAdapter.addFragment(new ThursdayFragment(), "Payshanba");
        pagerAdapter.addFragment(new FridayFragment(), "Juma");
        pagerAdapter.addFragment(new SundayFragment(), "Shanba");
        pagerAdapter.addFragment(new SaturdayFragment(), "Yakshanba");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}