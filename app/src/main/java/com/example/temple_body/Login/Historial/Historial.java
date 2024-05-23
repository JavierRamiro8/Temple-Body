package com.example.temple_body.Login.Historial;

public class Historial {
    private int peso;
    private int repeticiones;
    private int series;
    private String fecha;
    private String nombreEjercicio;

    public Historial(String nombreEjercicio, int peso, int repeticiones, int series, String fecha) {
        this.nombreEjercicio = nombreEjercicio;
        this.peso = peso;
        this.repeticiones = repeticiones;
        this.series = series;
        this.fecha=fecha;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }
    public int getPeso() {
        return peso;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public int getSeries() {
        return series;
    }

    public String getFecha() {
        return fecha;
    }
}

