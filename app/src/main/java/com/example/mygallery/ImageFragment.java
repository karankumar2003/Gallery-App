package com.example.mygallery;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class ImageFragment extends Fragment {

    public static ImageFragment newInstance(String image) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString("currentImagePath",image);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        String currentImagePath = getArguments().getString("currentImagePath");
        SubsamplingScaleImageView imageView = view.findViewById(R.id.fullImage);
        imageView.setOrientation(SubsamplingScaleImageView.ORIENTATION_USE_EXIF);
        imageView.setImage(ImageSource.uri(currentImagePath));

        return view;
    }
}