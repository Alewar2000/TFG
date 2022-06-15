package com.example.tfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tfg.bbdd.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Button btnlogout, btn_productos_venta;
    TextView nombreU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nombreU = findViewById(R.id.nombreUsuario);
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.user);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Integer usurping = prefs.getInt("usuariologin", 0);
        nombreU.setText(getUsuario(usurping));

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
            }
        });

        btnlogout = findViewById(R.id.logout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("usuariologin", 0);
                editor.commit();
                Intent i = new Intent(getApplicationContext(), UsuarioNoRegistrado.class);
                startActivity(i);
                finish();
            }
        });

        btn_productos_venta = findViewById(R.id.btn_productos_venta);
        btn_productos_venta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ProductosVentaActivity.class);
                startActivity(i);
            }
        });





    }

    public String getUsuario(Integer ida){
        DBHelper dbHelper = new DBHelper(this, "ecoeco.db", null);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Usuario> listaArrayUsuario = new ArrayList<Usuario>();
        Producto usuarioa = null;
        Cursor cursorUsuario = null;
        cursorUsuario = db.rawQuery("SELECT * FROM usuario where id="+ ida, null);

        if (cursorUsuario.moveToNext()){
            do {
                usuarioa = new Producto();
                usuarioa.setNombre(cursorUsuario.getString(3));
            }while (cursorUsuario.moveToNext());
        }
        cursorUsuario.close();
        return usuarioa.getNombre();
    }


}