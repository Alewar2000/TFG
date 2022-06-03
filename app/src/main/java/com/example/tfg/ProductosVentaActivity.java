package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class ProductosVentaActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_venta);

        recyclerView = findViewById(R.id.lista_productos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,llm.getOrientation());
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ProductosVentaAdapter adapter = new ProductosVentaAdapter(mostrarProductoUsuario());

        recyclerView.setAdapter(adapter);

    }

    public ArrayList<Producto> mostrarProductoUsuario(){
        DBHelper dbHelper = new DBHelper(this, "ecoeco.db", null, 2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Producto> listaArrayProductos = new ArrayList<Producto>();
        Producto productoa = null;
        Cursor cursorProductos = null;

        cursorProductos = db.rawQuery("SELECT * FROM producto where id=6", null);

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