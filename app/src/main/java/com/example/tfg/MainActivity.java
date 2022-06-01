package com.example.tfg;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    BottomNavigationView bottomNavigationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.user:
                        startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.create:
                        startActivity(new Intent(getApplicationContext(), ProductActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }

                return false;
            }
        });



        DBHelper dbHelper = new DBHelper(MainActivity.this);


        Intent i = new Intent(MainActivity.this, JavaIntro.class);
        startActivity(i);

        ImageCarousel carousel = findViewById(R.id.carousel);

        // Register lifecycle. For activity this will be lifecycle/getLifecycle() and for fragments it will be viewLifecycleOwner/getViewLifecycleOwner().
        carousel.registerLifecycle(getLifecycle());

        List<CarouselItem> list = new ArrayList<>();
        String imageUrl;
        String caption;
        list.add(
                new CarouselItem(
                        imageUrl = "https://images.unsplash.com/photo-1532581291347-9c39cf10a73c?w=1080",
                        caption = "Photo by Aaron Wu on Unsplash"
                )
        );
        list.add(
                new CarouselItem(
                        imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080",
                        caption = "Photo by Johannes Plenio on Unsplash"
                )
        );

        carousel.addData(list);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }
}