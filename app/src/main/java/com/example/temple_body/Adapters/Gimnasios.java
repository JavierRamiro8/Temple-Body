package com.example.temple_body.Adapters;

import java.util.ArrayList;

public class Gimnasios {
    private String nombre;
    private int precio;
    private double valoracion;





    public Gimnasios(String nombre, int precio, double valoracion) {
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
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public double getValoracion() {
        return valoracion;
    }
    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }


    /*
     * */
    public static ArrayList<Gimnasios> generador() {
        ArrayList<Gimnasios> gimnasios = new ArrayList<Gimnasios>();
        gimnasios.add(new Gimnasios("Gimnasio 1", 25, 4.6));
        gimnasios.add(new Gimnasios("Gimnasio 2", 20, 3.8));
        gimnasios.add(new Gimnasios("Gimnasio 3", 50, 4.9));
        gimnasios.add(new Gimnasios("Gimnasio 4", 20, 4.0));
        gimnasios.add(new Gimnasios("Gimnasio 5", 29, 2.6));
        gimnasios.add(new Gimnasios("Gimnasio 6", 35, 5.0));
        gimnasios.add(new Gimnasios("Gimnasio 7", 45, 1.0));
        gimnasios.add(new Gimnasios("Gimnasio 8", 20, 3.4));
        gimnasios.add(new Gimnasios("Gimnasio 9", 15, 4.6));
        gimnasios.add(new Gimnasios("Gimnasio 10", 10, 1.7));
        gimnasios.add(new Gimnasios("Gimnasio 11", 33, 3.3));
        gimnasios.add(new Gimnasios("Gimnasio 12", 15, 2.6));
        gimnasios.add(new Gimnasios("Gimnasio 13", 40, 4.1));
        gimnasios.add(new Gimnasios("Gimnasio 14", 39, 4.3));

        return gimnasios;
    }
}
