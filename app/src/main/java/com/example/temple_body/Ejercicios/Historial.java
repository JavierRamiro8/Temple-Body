package com.example.temple_body.Ejercicios;

public class Historial {
    private int peso;
    private int repeticiones;
    private int series;

    public Historial(int peso, int repeticiones, int series) {
        this.peso = peso;
        this.repeticiones = repeticiones;
        this.series = series;
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
}

