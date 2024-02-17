package com.example.temple_body.Ejercicios.llamadaAPIMusculos;

public class Ejercicio {
    private String name;
    private String muscle;
    private String difficulty;

    public Ejercicio(String name, String muscle, String difficulty) {
        this.name=name;
        this.muscle=muscle;
        this.difficulty=difficulty;
    }

    public String getName() {
        return name;
    }
    public String getMuscle() {
        return muscle;
    }
    public String getDifficulty() {
        return difficulty;
    }
}
