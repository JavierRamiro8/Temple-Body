package com.example.temple_body.Gimnasios;

import com.example.temple_body.Settings.Textos;

import java.util.ArrayList;

public class Gimnasios {
    private String nombre;
    private double precio;
    private double valoracion;
    private String masInfo;





    public Gimnasios(String nombre, double precio, double valoracion, String masInfo) {
        this.nombre = nombre;
        this.precio = precio;
        this.valoracion = valoracion;
        this.masInfo = masInfo;
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

    public String getMasInfo() {
        return masInfo;
    }

    public static ArrayList<Gimnasios> generador() {
        ArrayList<Gimnasios> gimnasios = new ArrayList<Gimnasios>();

        gimnasios.add(new Gimnasios("Fitness Park", 25, 4.1, Textos.FITNESS_PARK_INFO));
        gimnasios.add(new Gimnasios("Basic Fit", 24.99, 3.6, Textos.BASIC_FIT_INFO));
        gimnasios.add(new Gimnasios("Forus", 54.40, 3.9, Textos.FORUS_INFO));
        gimnasios.add(new Gimnasios("Alta Fit", 37.90, 4.0, Textos.ALTA_FIT_INFO));
        gimnasios.add(new Gimnasios("Viva gym", 29.90, 4.2, Textos.VIVA_GYM_INFO));
        gimnasios.add(new Gimnasios("Synergym", 24.99, 4.2, Textos.SYNERGYM_INFO));
        gimnasios.add(new Gimnasios("DreamFit", 34.90, 4.2, Textos.DREAMFIT_INFO));
        gimnasios.add(new Gimnasios("McFit", 29.90, 4.1, Textos.MCFIT_INFO));
        gimnasios.add(new Gimnasios("Go-Fit", 47.45, 4.2, Textos.GO_FIT_INFO));
        gimnasios.add(new Gimnasios("Holiday gym", 49, 3.6, Textos.HOLIDAY_GYM_INFO));

        return gimnasios;
    }
}
