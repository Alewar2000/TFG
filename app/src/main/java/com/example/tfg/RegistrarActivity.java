package com.example.tfg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tfg.bbdd.daoUsuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {
    EditText correo, pass, nombre, apellido;
    Button btnRegistrar;
    ImageButton atras;
    com.example.tfg.bbdd.daoUsuario daoUsuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        correo = findViewById(R.id.et_correo_register);
        pass = findViewById(R.id.et_pass_register);
        nombre = findViewById(R.id.et_nombre_register);
        apellido = findViewById(R.id.et_apellidos_register);
        atras = findViewById(R.id.atras);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(this);

        atras.setOnClickListener(this);
        daoUsuario = new daoUsuario(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.atras:
                onBackPressed();
                break;
            case R.id.btnRegistrar:
                Pattern pat = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
                Matcher mather = pat.matcher(correo.getText().toString());
                Usuario u = new Usuario();
                if (mather.find() == true) {
                    u.setCorreo(correo.getText().toString());
                } else {
                    Toast.makeText(this, "Correo invalido", Toast.LENGTH_SHORT).show();
                }
                u.setNombre(nombre.getText().toString());
                u.setApellidos(apellido.getText().toString());
                u.setPassword(pass.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
                }else if(!u.isNullCorreo()){
                    Toast.makeText(this, "Correo invalido", Toast.LENGTH_SHORT).show();
                } else if (daoUsuario.insertarUsuario(u)){
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("usuariologin", u.getId());
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(this, "El correo ya esta vinculado ha una cuenta", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    public void insertarCorreo(){



    }
}
