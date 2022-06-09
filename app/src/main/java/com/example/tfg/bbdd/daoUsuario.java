package com.example.tfg.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tfg.Usuario;

import java.util.ArrayList;

public class daoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "ecoeco.db";


    public daoUsuario(Context c){
        this.c = c;
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        u = new Usuario();

    }

    public boolean insertarUsuario(Usuario u){
        if (buscar(u.getCorreo())==0){
            ContentValues cv = new ContentValues();
            cv.put("nombre", u.getNombre());
            cv.put("correo", u.getCorreo());
            cv.put("apellido", u.getApellidos());
            cv.put("password", u.getPassword());
            //cv.put("direccion", u.getDireccion());
            return (sql.insert("usuario",null, cv)>0);
        }else {
            return false;
        }

    }

    public int buscar(String c){
        int x = 0;
        lista= selectUsuarios();
        for (Usuario us:lista){
            if (us.getCorreo().equals(c)){
                x++;
            }
        }
    return x;
    }

    public ArrayList<Usuario> selectUsuarios(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if (cr != null&&cr.moveToNext()){
            do {
                Usuario u= new Usuario();
                u.setId(cr.getInt(0));
                u.setCorreo(cr.getString(1));
                u.setPassword(cr.getString(2));
                u.setNombre(cr.getString(3));
                u.setApellidos(cr.getString(4));
                u.setDireccion(cr.getString(5));
                lista.add(u);
            }while (cr.moveToNext());
        }
        return lista;
    }

    public int login(String correo, String pass){
        int a = 0;
        Cursor cr =sql.rawQuery("select * from usuario",null);
        if (cr!=null&&cr.moveToNext()){
            do {
                if (cr.getString(1).equals(correo)&&cr.getString(2).equals(pass)){
                    a++;
                }
            }while (cr.moveToNext());


        }
        return a;
    }

    public Usuario getUsuario(String correo, String pass){
        lista = selectUsuarios();
        for (Usuario u:lista){
            if (u.getCorreo().equals(correo)&&u.getPassword().equals(pass)){
                return u;
            }
        }
        return null;

    }



}
