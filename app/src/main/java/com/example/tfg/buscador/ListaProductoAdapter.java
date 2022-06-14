package com.example.tfg.buscador;

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

import com.example.tfg.Producto;
import com.example.tfg.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class ListaProductoAdapter extends RecyclerView.Adapter<ListaProductoAdapter.ProductoViewHolder> {

    ArrayList<Producto> listaProductos;
    OnProductoListener monProductoListener;

    public ListaProductoAdapter(ArrayList<Producto> listaProductos, OnProductoListener onProductoListener){
        this.listaProductos= listaProductos;
        this.monProductoListener =onProductoListener;

    }


    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto,null, false);
        return new ProductoViewHolder(view, monProductoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.nombre.setText(listaProductos.get(position).getNombre());
        holder.descripcion.setText(listaProductos.get(position).getDescripcion());
        holder.precio.setText(listaProductos.get(position).getPrecio().toString());
        File imgFile = new File(listaProductos.get(position).getImagen());
        if(imgFile.exists()){
            //Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            //holder.imagen.setImageBitmap(myBitmap);
            Picasso.with(holder.itemView.getContext()).load(imgFile).resize(300, 300).centerCrop().into(holder.imagen);
        }
    }

    @Override
    public int getItemCount() {

        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre, descripcion, precio;
        ImageView imagen;
        OnProductoListener onProductoListener;

        public ProductoViewHolder(@NonNull View itemView, OnProductoListener onProductoListener) {
            super(itemView);
            nombre = itemView.findViewById(R.id.viewNombre);
            descripcion = itemView.findViewById(R.id.viewDescripcion);
            precio = itemView.findViewById(R.id.viewPrecio);
            imagen = itemView.findViewById(R.id.imagenlista);
            this.onProductoListener= onProductoListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onProductoListener.onProductoClick(getAdapterPosition());
        }
    }

    public interface OnProductoListener{
        void onProductoClick(int position);
    }
}
