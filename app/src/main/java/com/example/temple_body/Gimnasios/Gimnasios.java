package com.example.temple_body.Gimnasios;

import java.util.ArrayList;

public class Gimnasios {
    private String nombre;
    private double precio;
    private double valoracion;





    public Gimnasios(String nombre, double precio, double valoracion) {
        this.nombre = nombre;
        this.precio = precio;
        this.valoracion = valoracion;

    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getValoracion() {return valoracion;}
    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }


    /*
     * */
    public static ArrayList<Gimnasios> generador() {
        ArrayList<Gimnasios> gimnasios = new ArrayList<Gimnasios>();
        gimnasios.add(new Gimnasios("Fitness Park", 25, 4.1));
        gimnasios.add(new Gimnasios("Basic Fit", 24.99, 3.6));
        gimnasios.add(new Gimnasios("Forus", 54.40, 3.9));
        gimnasios.add(new Gimnasios("Alta Fit", 37.90, 4.0));
        gimnasios.add(new Gimnasios("Viva gym", 29.90, 4.2));
        gimnasios.add(new Gimnasios("Synergym", 24.99, 4.2));
        gimnasios.add(new Gimnasios("DreamFit", 34.90, 4.2));
        gimnasios.add(new Gimnasios("McFit", 29.90, 4.1));
        gimnasios.add(new Gimnasios("Go-Fit", 47.45, 4.2));
        gimnasios.add(new Gimnasios("Holiday gym", 49, 3.6));

        return gimnasios;
    }
}
