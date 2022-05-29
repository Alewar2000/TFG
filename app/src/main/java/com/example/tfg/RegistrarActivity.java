package com.example.tfg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {
    EditText correo, pass, nombre, apellido;
    Button btnRegistrar;
    ImageButton atras;
    daoUsuario daoUsuario;
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
                Usuario u = new Usuario();
                u.setCorreo(correo.getText().toString());
                u.setNombre(nombre.getText().toString());
                u.setApellidos(apellido.getText().toString());
                u.setPassword(pass.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
                }else if (daoUsuario.insertarUsuario(u)){
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "El correo ya esta vinculado ha una cuenta", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
