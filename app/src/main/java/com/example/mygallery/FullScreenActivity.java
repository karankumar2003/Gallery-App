package com.example.mygallery;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import java.util.ArrayList;


public class FullScreenActivity extends FragmentActivity {
    ArrayList<String> imageList = new ArrayList<>();
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_view);



            imageList = getIntent().getStringArrayListExtra("imageList");

        int position = getIntent().getIntExtra("position",0);

        viewPager = findViewById(R.id.ViewPager);

        ImageFragmentAdapter imageFragmentAdapter = new ImageFragmentAdapter(this,imageList);
        viewPager.setAdapter(imageFragmentAdapter);
        viewPager.setCurrentItem(position,false);



    }
}