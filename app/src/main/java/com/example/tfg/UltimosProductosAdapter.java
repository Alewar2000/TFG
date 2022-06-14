package com.example.tfg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class UltimosProductosAdapter extends RecyclerView.Adapter<UltimosProductosAdapter.ProductoViewHolder> {
    ArrayList<Producto> listaProductos;
    private Context context;

    public UltimosProductosAdapter(ArrayList<Producto> listaProductos){
        this.listaProductos= listaProductos;

    }

    @NonNull
    @Override
    public UltimosProductosAdapter.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_home,null, false);
        return new UltimosProductosAdapter.ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UltimosProductosAdapter.ProductoViewHolder holder, int position) {
        holder.nombre.setText(listaProductos.get(position).getNombre());
        holder.precio.setText(listaProductos.get(position).getPrecio().toString() + "€");
        File imgFile = new File(listaProductos.get(position).getImagen());
        if(imgFile.exists()){
            //Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            //holder.imagen.setImageBitmap(myBitmap);
            Picasso.with(holder.itemView.getContext()).load(imgFile).resize(600, 600).centerCrop().into(holder.imagen);
        }
        //Drawable image = Drawable.createFromPath(listaProductos.get(position).getImagen());
        //Picasso.with(holder.itemView.getContext()).load(image).resize(300, 300).centerCrop().into(holder.imagen);
        //holder.imagen.setImageDrawable(image);
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio;
        ImageView imagen;


        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tv_nombre);
            precio = itemView.findViewById(R.id.tv_precio);
            imagen = itemView.findViewById(R.id.img_prod);

        }
    }
}
