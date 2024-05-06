package com.example.temple_body.Gimnasios;

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
    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    public String getMasInfo() {
        return masInfo;
    }
    public void setMasInfo(String masInfo) {
        this.masInfo = masInfo;
    }

    /*
     * */
    public static ArrayList<Gimnasios> generador() {
        ArrayList<Gimnasios> gimnasios = new ArrayList<Gimnasios>();
        gimnasios.add(new Gimnasios("Fitness Park", 25, 4.1, "Tiene las mejores máquinas y equipamiento actual."));
        gimnasios.add(new Gimnasios("Basic Fit", 24.99, 3.6, "Tiene clases guiadas, pero si buscas el mejor entrenamiento, no es la mejor opción."));
        gimnasios.add(new Gimnasios("Forus", 54.40, 3.9, "Equipado con piscina, y muchas clases a las que asistir."));
        gimnasios.add(new Gimnasios("Alta Fit", 37.90, 4.0, "Buena maquinaria, y diferentes clases guiadas con monitor o virtuales."));
        gimnasios.add(new Gimnasios("Viva gym", 29.90, 4.2, "Bien equipado, y muchas opciones de clases guiadas."));
        gimnasios.add(new Gimnasios("Synergym", 24.99, 4.2, "Muy buena variedad de máquinas y clases disponibles. Son normalmente clubes urbanos bastante amplios, además de económicos"));
        gimnasios.add(new Gimnasios("DreamFit", 34.90, 4.2, "Uno de los mejores gimnasios actualmente, con las mejores marcas de máquinas y equipamiento, además de la mayor oferta de clases guiadas del mercado."));
        gimnasios.add(new Gimnasios("McFit", 29.90, 4.1, "Actualmente calidad precio, es una de las mejores opciones, además de buena maquinaria, dispone de bastantes clases a las que asistir."));
        gimnasios.add(new Gimnasios("Go-Fit", 47.45, 4.2, "Tiene parking, piscina, mucha variedad de clases guiadas, y buena maquinaria. Aunque es de los más caros, tambien es de las mejores opciones actualmente."));
        gimnasios.add(new Gimnasios("Holiday gym", 49, 3.6, "Buena maquinaria, aunque no hay muchos gimnasios disponibles."));

        return gimnasios;
    }
}
