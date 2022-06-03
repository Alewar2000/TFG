package com.example.tfg;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;


import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    BottomNavigationView bottomNavigationView;
    DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this, "ecoeco.db",null,2);

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
                        imageUrl = "https://img.freepik.com/vector-gratis/plantilla-banner-producto-100-ciento-natural-cesta-compra-verduras-organicas-frescas-tienda-alimentos-ecologicos-mercado-agricola-embalaje-diseno-publicitario-ilustracion-vectorial-plana_609547-165.jpg?w=2000"
                )
        );
        list.add(
                new CarouselItem(
                        imageUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fes.123rf.com%2Fphoto_64167576_banner-de-producto-ecol%25C3%25B3gico-con-personajes-de-verduras-de-dibujos-animados-aislado-sobre-fondo-amarill.html&psig=AOvVaw0I1aDfxOaqaADEdY9nmTn_&ust=1654331601110000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCOCH39XvkPgCFQAAAAAdAAAAABAD"
                )
        );
        carousel.setCarouselListener(new CarouselListener() {

            @Nullable
            @Override
            public ViewBinding onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
                return null;
            }
            @Override
            public void onBindViewHolder(@NonNull ViewBinding viewBinding, @NonNull CarouselItem carouselItem, int i) {

            }

            @Override
            public void onClick(int i, @NonNull CarouselItem carouselItem) {
            if (i==1){
                Intent ia = new Intent(MainActivity.this, JavaIntro.class);
                startActivity(ia);
            }
            }
            @Override
            public void onLongClick(int i, @NonNull CarouselItem carouselItem) {

            }


        });

        carousel.addData(list);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }
}