package com.example.tfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText correo, pass;
    Button btnEntrar, btnRegistrar;
    daoUsuario daoUsuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        correo = findViewById(R.id.et_correo_login);
        pass = findViewById(R.id.et_pass_login);
        btnEntrar = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegister);

        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        daoUsuario = new daoUsuario(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                String c= correo.getText().toString();
                String p= pass.getText().toString();
                if (c.equals("")||p.equals("")){
                    Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show();
                }else if (daoUsuario.login(c,p)==1){
                    
                }else {
                    Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRegister:
                Intent i = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(i);

                break;
        }
    }
}
