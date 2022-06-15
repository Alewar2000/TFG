package com.example.tfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tfg.bbdd.DBHelper;
import com.example.tfg.buscador.BuscadorActivity;
import com.example.tfg.buscador.ListaProductoAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;


import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DBHelper dbHelper;
    RecyclerView ultimos_productos;
    EditText search;
    ImageButton btn_search;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Integer usurping = prefs.getInt("usuariologin", 0);
        search = findViewById(R.id.et_search);
        btn_search = findViewById(R.id.btn_search);

        dbHelper = new DBHelper(this, "ecoeco.db",null);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        ultimos_productos = findViewById(R.id.ultimos_productos);
        ultimos_productos.setLayoutManager(layoutManager);

        UltimosProductosAdapter adapter = new UltimosProductosAdapter(mostrarultimosProducto());

        ultimos_productos.setAdapter(adapter);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    return true;
                case R.id.user:
                    if (usurping==0){
                        startActivity(new Intent(getApplicationContext(), UsuarioNoRegistrado.class));
                        overridePendingTransition(0,0);
                        finish();
                    }else {
                        startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                    }
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
        });

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
                        imageUrl = "https://www.eroski.es/wp-content/uploads/2017/05/OG-ecolo%CC%81gicos_ES-1.jpg"
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
            if (i==0){
                Intent izz = new Intent(MainActivity.this, BuscadorActivity.class);
                startActivity(izz);
            }

            }
            @Override
            public void onLongClick(int i, @NonNull CarouselItem carouselItem) {

            }


        });

        carousel.addData(list);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BuscadorActivity.class);
                intent.putExtra("busqueda", search.getText().toString());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
            edit.putInt("usuariologin", 0);
            edit.commit();
            Intent i = new Intent(MainActivity.this, JavaIntro.class);
            startActivity(i);
        }
        super.onResume();
    }

    public ArrayList<Producto> mostrarultimosProducto(){
        DBHelper dbHelper = new DBHelper(this, "ecoeco.db", null);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Producto> listaArrayProductos = new ArrayList<Producto>();
        Producto productoa = null;
        Cursor cursorProductos = null;

        cursorProductos = db.rawQuery("SELECT * FROM producto ORDER BY id DESC LIMIT 5", null);

        if (cursorProductos.moveToNext()){
            do {
                productoa = new Producto();
                productoa.setId(cursorProductos.getInt(0));
                productoa.setNombre(cursorProductos.getString(1));
                productoa.setDescripcion(cursorProductos.getString(2));
                productoa.setPrecio(cursorProductos.getInt(3));
                productoa.setImagen(cursorProductos.getString(6));


                listaArrayProductos.add(productoa);
            }while (cursorProductos.moveToNext());


        }
        cursorProductos.close();

        return listaArrayProductos;
    }

}