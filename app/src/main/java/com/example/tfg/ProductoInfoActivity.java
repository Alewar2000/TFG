package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tfg.bbdd.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ProductoInfoActivity extends AppCompatActivity {

    ImageView imagenInfo;
    TextView tv_titulo, tv_precio, tv_descripcion;
    Producto producto;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_info);
        Integer idproducto = getIntent().getExtras().getInt("idproducto");

        imagenInfo = findViewById(R.id.imageview_info);
        tv_titulo = findViewById(R.id.tv_titulo_info);
        tv_precio = findViewById(R.id.tv_precio_info);
        tv_descripcion = findViewById(R.id.tv_descripcion_info);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Integer usurping = prefs.getInt("usuariologin", 0);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    finish();
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

        producto= setproducto(idproducto);
        File imgFile = new File(producto.getImagen());
        Picasso.with(this).load(imgFile).resize(1100,1080).centerCrop().into(imagenInfo);
        tv_titulo.setText(producto.getNombre());
        tv_descripcion.setText(producto.getDescripcion());
        tv_precio.setText(producto.getPrecio().toString());
    }

    public Producto setproducto(Integer idproducto){
        DBHelper dbHelper = new DBHelper(this, "ecoeco.db", null);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Producto productoa = null;
        Cursor cursorProductos = null;

        cursorProductos = db.rawQuery("SELECT * FROM producto where id="+idproducto, null);

        if (cursorProductos.moveToNext()){
            do {
                productoa = new Producto();
                productoa.setId(cursorProductos.getInt(0));
                productoa.setNombre(cursorProductos.getString(1));
                productoa.setDescripcion(cursorProductos.getString(2));
                productoa.setPrecio(cursorProductos.getInt(3));
                productoa.setImagen(cursorProductos.getString(6));

            }while (cursorProductos.moveToNext());
        }
        cursorProductos.close();

    return productoa;
    }



}