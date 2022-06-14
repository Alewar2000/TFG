package com.example.tfg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.tfg.bbdd.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ProductActivity extends AppCompatActivity {

    private final String CARPETA_RAIZ="/data/data/com.example.tfg/files/";
    final int COD_SELECCIONA = 10;
    final int COD_FOTO = 20;


    BottomNavigationView bottomNavigationView;
    DBHelper dbHelper;
    Button btnInsertar;
    ImageButton btnImagen;
    EditText etnombre, etprecio, etcantidad, etdescripcion;
    ImageView imagen;
    String path;
    String pathsSqlite;

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
        btnImagen = findViewById(R.id.btnCargarImagen);
        imagen = findViewById(R.id.ImagenId);


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
        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });



    }

    private void cargarImagen() {
        final CharSequence[] opciones= {"Tomar foto", "Cargar Imagen", "Cancelar"};
        final AlertDialog.Builder alertaOpciones = new AlertDialog.Builder(ProductActivity.this);
        alertaOpciones.setTitle("Seleccione una Opción");
        alertaOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar foto")){
                    tomarFotografia();
                }else {
                    if(opciones[i].equals("Cargar Imagen")){
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccionar la aplicación"),COD_SELECCIONA);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        alertaOpciones.show();


    }

    private void tomarFotografia() {

        String nombreImagen="";
        nombreImagen = (System.currentTimeMillis()/1000)+ ".jpg";
        path = CARPETA_RAIZ+nombreImagen;
        File FileImage = new File(path);
        pathsSqlite = path;

        Uri uri = FileProvider.getUriForFile(
                this,
                BuildConfig.APPLICATION_ID + "." + getLocalClassName() + ".provider",
                FileImage);
        Intent intentt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentt.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intentt, COD_FOTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case COD_SELECCIONA:
                    Uri imageUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                        persistImage(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imagen.setImageURI(imageUri);
                    //Stringuri = imageUri.toString();
                    break;
                case COD_FOTO:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String s, Uri uri) {
                            Log.i("Ruta de almacenamiento","Path: " + path);
                        }
                    });
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    //Picasso.with(this).load(path).resize(400,400).into(imagen);
                    imagen.setRotation(90);
                    imagen.setImageBitmap(bitmap);

                    break;
            }


        }
    }


    private void persistImage(Bitmap bitmap) {
        String nombreImagen="";
        nombreImagen = (System.currentTimeMillis()/1000)+ ".jpg";
        File imageFile = new File(CARPETA_RAIZ, nombreImagen);
        pathsSqlite = CARPETA_RAIZ + nombreImagen;
        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {

        }
    }

    public void añadirProducto(){
        dbHelper = new DBHelper(this, "ecoeco.db",null);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String nombre = etnombre.getText().toString();
        Integer precio = Integer.parseInt(etprecio.getText().toString());
        Integer unidades = Integer.parseInt(etcantidad.getText().toString());
        String descripcion = etdescripcion.getText().toString();
        String imagen = pathsSqlite;
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setUnidades(unidades);
        producto.setDescripcion(descripcion);
        producto.setImagen(imagen);
        producto.setIdvendedor(6);

        ContentValues cv = new ContentValues();
        cv.put("nombre", producto.getNombre());
        cv.put("descripcion", producto.getDescripcion());
        cv.put("precio", producto.getPrecio());
        cv.put("stock", producto.getUnidades());
        cv.put("imagen", producto.getImagen());
        cv.put("idvendedor", producto.getIdvendedor());
        db.insert("producto",null, cv);

    }
}