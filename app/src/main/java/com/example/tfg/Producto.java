package com.example.tfg;

public class Producto {
    Integer id;
    String nombre;
    Integer precio;
    Integer unidades;
    String descripcion;
    Integer idvendedor;
    String imagen;


    public Producto() {
    }

    public Producto(Integer id, String nombre, Integer precio, Integer unidades, String descripcion, Integer idvendedor, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.idvendedor = idvendedor;
        this.imagen = imagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(Integer idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
