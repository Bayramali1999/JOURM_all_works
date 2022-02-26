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
import com.example.jourm.ui.main_view.grade_view.GradeItemFragment;
import com.example.jourm.widget.CalendarPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class GradeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CalendarPageAdapter pageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grade, container, false);
        pageAdapter = new CalendarPageAdapter(getActivity().getSupportFragmentManager());
        tabLayout = view.findViewById(R.id.grade_tl);
        viewPager = view.findViewById(R.id.grade_vp);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pageAdapter.addFragment(new GradeItemFragment(), "Kuzgi semestr 2021-2022");
        pageAdapter.addFragment(new GradeItemFragment(), "Kuzgi semestr 2020-2021");
        pageAdapter.addFragment(new GradeItemFragment(), "Bahorgi semestr 2021-2022");

        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}