package com.example.temple_body.Ejercicios.Historial;

public class Historial {
    private int peso;
    private int repeticiones;
    private int series;
    private String fecha;

    public Historial(int peso, int repeticiones, int series, String fecha) {
        this.peso = peso;
        this.repeticiones = repeticiones;
        this.series = series;
        this.fecha=fecha;
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

