package com.example.mygallery;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ImageFragmentAdapter extends FragmentStateAdapter {
    List<String> imageList;

    public ImageFragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<String> imageList) {
        super(fragmentActivity);
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return ImageFragment.newInstance(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
