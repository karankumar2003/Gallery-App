package com.example.mygallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;
import java.text.ParseException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_READ_EXTERNAL_STORAGE=1;
    ArrayList<String> imageList= new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewAdapter = new RecyclerViewAdapter(this,imageList);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,CalculateNoOfColumns()));

        checkAndRequestReadExternalStoragePermission();

        recyclerView.setAdapter(recyclerViewAdapter);

        }


    private void checkAndRequestReadExternalStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this,"Permission is required to access images.",Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_EXTERNAL_STORAGE);
            }
        }else {
            try {
                getAllImages();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,imageList);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE){
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                try {
                    getAllImages();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,imageList);

                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new GridLayoutManager(this,CalculateNoOfColumns()));

                recyclerView.setAdapter(recyclerViewAdapter);
            }else
            {
                Toast.makeText(this, "Storage permission is required to display images", Toast.LENGTH_SHORT).show();

            }
        }
    }


    private void getAllImages() throws ParseException {
        String[] projection = { MediaStore.Images.Media.DATA };
        String sortOrder = MediaStore.Images.Media.DATE_TAKEN + " DESC";
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, sortOrder);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                String filePath = cursor.getString(column_index);
                imageList.add(filePath);
            }
            cursor.close();
        }
    }



    private int CalculateNoOfColumns(){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels/displayMetrics.density;
        int noOfColumns = (int) (dpWidth/90);
        Log.d("MainActivity",String.valueOf (noOfColumns));
        return noOfColumns;
    }
}