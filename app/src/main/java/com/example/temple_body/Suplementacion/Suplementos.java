package com.example.temple_body.Suplementacion;

import java.util.ArrayList;

public class Suplementos {
    private String nombre,descripcion;

    public Suplementos(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    /*
     * */
    public static ArrayList<Suplementos> generador() {
        ArrayList<Suplementos> proteinas = new ArrayList<Suplementos>();
        proteinas.add(new Suplementos("Whey 80", "El Concentrado Whey Protein 80 ha sido desarrollado sin hacer concesión alguna, utilizando exclusivamente el mejor concentrado de proteína de suero, ofreciendo un descomunal 82% de contenido de proteínas"));
        proteinas.add(new Suplementos("Isolate whey", "La proteína aislada es una fuente de este macronutriente, que se caracteriza por contar con una pureza entorno al 90%. El contenido de grasas e hidratos de carbono que posee es prácticamente inexistente."));
        proteinas.add(new Suplementos("Caseina", "La caseína es una proteína de un alto valor biológico que contiene minerales importantes como el calcio y el fósforo, así como los 9 aminoácidos esenciales que tu cuerpo no puede producir por sí mismo y que necesita para ayudar a construir músculo, como la leucina, isoleucina y valina."));
        return proteinas;
    }
}
