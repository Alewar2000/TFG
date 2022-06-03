package com.example.tfg;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductosVentaAdapter extends RecyclerView.Adapter<ProductosVentaAdapter.ProductoViewHolder>{

    ArrayList<Producto> listaProductos;
    public ProductosVentaAdapter(ArrayList<Producto> listaProductos){
        this.listaProductos= listaProductos;

    }

    @NonNull
    @Override
    public ProductosVentaAdapter.ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_venta,null, false);
        return new ProductosVentaAdapter.ProductoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductosVentaAdapter.ProductoViewHolder holder, int position) {
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
        Button btnEditar, BtnRemove;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.viewNombre);
            descripcion = itemView.findViewById(R.id.viewDescripcion);
            precio = itemView.findViewById(R.id.viewPrecio);
            imagen = itemView.findViewById(R.id.imagenlista);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            BtnRemove = itemView.findViewById(R.id.btnRemove);

        }
    }
}
