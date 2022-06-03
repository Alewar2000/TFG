package com.example.tfg;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaProductoAdapter extends RecyclerView.Adapter<ListaProductoAdapter.ProductoViewHolder> {

    ArrayList<Producto> listaProductos;
    public ListaProductoAdapter(ArrayList<Producto> listaProductos){
        this.listaProductos= listaProductos;

    }


    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_producto,null, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.nombre.setText(listaProductos.get(position).getNombre());
        holder.descripcion.setText(listaProductos.get(position).getDescripcion());
        holder.precio.setText(listaProductos.get(position).getPrecio().toString());

        Drawable image = Drawable.createFromPath(listaProductos.get(position).getImagen());
        holder.imagen.setImageDrawable(image);
    }

    @Override
    public int getItemCount() {

        return listaProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, descripcion, precio;
        ImageView imagen;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.viewNombre);
            descripcion = itemView.findViewById(R.id.viewDescripcion);
            precio = itemView.findViewById(R.id.viewPrecio);
            imagen = itemView.findViewById(R.id.imagenlista);

        }
    }
}
