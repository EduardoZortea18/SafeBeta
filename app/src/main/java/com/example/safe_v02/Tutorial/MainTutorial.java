package com.example.safe_v02.Tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.safe_v02.R;
import com.example.safe_v02.Tutorial2;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainTutorial extends AppCompatActivity {
ViewPager viewPager;
PagerAdapter pagerAdapter;
TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Tutorial1());
        fragmentList.add(new Tutorial2());

        viewPager = (ViewPager)findViewById(R.id.pagerTutorial);
        pagerAdapter= new SlidePagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout)findViewById(R.id.tabsTutorial);
        tabLayout.setupWithViewPager(viewPager);
    }
}
