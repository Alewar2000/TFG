package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class BuscadorActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

        recyclerView = findViewById(R.id.lista_productos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ListaProductoAdapter adapter = new ListaProductoAdapter(mostrarProducto());

        recyclerView.setAdapter(adapter);
    }


    public ArrayList<Producto> mostrarProducto(){
        DBHelper dbHelper = new DBHelper(this, "ecoeco.db", null, 2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Producto> listaArrayProductos = new ArrayList<Producto>();
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
                //productoa.setImagen(cursorProductos.getString(6));

                listaArrayProductos.add(productoa);
            }while (cursorProductos.moveToNext());


        }
        //cursorProductos.close();

        return listaArrayProductos;
    }

}