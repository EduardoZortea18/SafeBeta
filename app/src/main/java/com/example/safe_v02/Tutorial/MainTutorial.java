package com.example.safe_v02.Tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.safe_v02.R;

public class MainTutorial extends AppCompatActivity {
ViewPager viewPager;
PagerAdapter pagerAdapter;
int cont=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_tutorial, new BoasVindas()).commit();
    }


}
