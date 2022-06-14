package com.example.tfg.buscador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.tfg.CartActivity;
import com.example.tfg.MainActivity;
import com.example.tfg.ProductActivity;
import com.example.tfg.ProductoInfoActivity;
import com.example.tfg.UserActivity;
import com.example.tfg.bbdd.DBHelper;
import com.example.tfg.Producto;
import com.example.tfg.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class BuscadorActivity extends AppCompatActivity implements  ListaProductoAdapter.OnProductoListener{

    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    ArrayList<Producto> listaArrayProductos = new ArrayList<Producto>();
    ListaProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

        recyclerView = findViewById(R.id.lista_ventas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ListaProductoAdapter(mostrarProducto(), this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    finish();
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
        });
    }


    public ArrayList<Producto> mostrarProducto(){
        DBHelper dbHelper = new DBHelper(this, "ecoeco.db", null);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        listaArrayProductos = new ArrayList<Producto>();
        Producto productoa = null;
        Cursor cursorProductos = null;

        cursorProductos = db.rawQuery("SELECT * FROM producto", null);

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

    @Override
    public void onProductoClick(int position) {
        Intent intent = new Intent(this, ProductoInfoActivity.class);
        intent.putExtra("idproducto", listaArrayProductos.get(position).getId());
        startActivity(intent);
    }
}