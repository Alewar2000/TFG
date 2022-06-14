package com.example.tfg.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NOMBRE = "ecoeco.db";
    public static final String TABLE_USUARIOS = "usuario";
    public static final String TABLE_PRODUCTOS= "producto";
    public static final String TABLE_PEDIDO= "pedido";
    public static final String TABLE_DETALLE_PEDIDOS ="d_pedido";
    public static final String TABLE_USUARIO_PEDIDO = "clientepedido";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_DETALLE_PEDIDOS +
                "(idproducto interger," +
                " idpedido integer," +
                " preciou integer," +
                " cantidad integer,"+
                "PRIMARY KEY(idproducto,idpedido))");

        db.execSQL("create table if not exists " + TABLE_USUARIO_PEDIDO +
                "(idusuario interger," +
                " idpedidou integer," +
                "PRIMARY KEY(idusuario,idpedidou))");

        db.execSQL("create table if not exists "+ TABLE_USUARIOS +
                "(id integer primary key autoincrement, " +
                "correo text, " +
                "password text," +
                "nombre text, " +
                "apellido text, " +
                "direccion text," +
                "FOREIGN KEY(id) REFERENCES " + TABLE_USUARIO_PEDIDO +"(idusuario))");

        db.execSQL("create table if not exists " + TABLE_PEDIDO +
                "(id integer primary key autoincrement," +
                " idcliente text," +
                " precio integer," +
                " fecha text," +
                "FOREIGN KEY(id) REFERENCES " + TABLE_USUARIO_PEDIDO +"(idpedidou), "+
                "FOREIGN KEY(id) REFERENCES " + TABLE_DETALLE_PEDIDOS +"(idpedido))");

        db.execSQL("create table if not exists " + TABLE_PRODUCTOS +
                "(id integer primary key autoincrement," +
                " nombre text," +
                " descripcion text," +
                " precio integer," +
                " stock integer," +
                " idvendedor integer, " +
                " imagen text, " +
                "FOREIGN KEY(id) REFERENCES " + TABLE_DETALLE_PEDIDOS +"(idproducto))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_USUARIOS);
        db.execSQL("DROP TABLE "+TABLE_PRODUCTOS);
        db.execSQL("DROP TABLE "+TABLE_PEDIDO);
        db.execSQL("DROP TABLE "+TABLE_DETALLE_PEDIDOS);
        db.execSQL("DROP TABLE "+TABLE_USUARIO_PEDIDO);
        onCreate(db);
    }




}
