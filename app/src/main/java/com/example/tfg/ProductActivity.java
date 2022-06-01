package com.example.tfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProductActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DBHelper dbHelper;
    Button btnInsertar;
    EditText etnombre, etprecio, etcantidad, etdescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.create);
        etnombre = findViewById(R.id.et_nombre);
        etprecio = findViewById(R.id.et_precio);
        etcantidad = findViewById(R.id.et_cantidad);
        etdescripcion = findViewById(R.id.et_descripcion);
        btnInsertar = findViewById(R.id.publicarr);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                        return true;
                }

                return false;
            }
        });




        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            añadirProducto();
            }
        });

    }




    public void añadirProducto(){
        dbHelper = new DBHelper(ProductActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String nombre = etnombre.getText().toString();
        Integer precio = Integer.parseInt(etprecio.getText().toString());
        Integer unidades = Integer.parseInt(etcantidad.getText().toString());
        String descripcion = etdescripcion.getText().toString();
        //String imagen = null;
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setUnidades(unidades);
        producto.setDescripcion(descripcion);
        //producto.setImagen(imagen);
        producto.setIdvendedor(6);

        ContentValues cv = new ContentValues();
        cv.put("nombre", producto.getNombre());
        cv.put("descripcion", producto.getDescripcion());
        cv.put("precio", producto.getPrecio());
        cv.put("stock", producto.getUnidades());
        //cv.put("imagen", producto.getImagen());
        cv.put("idvendedor", producto.getIdvendedor());
        db.insert("producto",null, cv);

    }
}