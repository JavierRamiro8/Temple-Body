package com.example.temple_body.Suplementacion;

import com.example.temple_body.Settings.Textos;

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


    public static ArrayList<Suplementos> generador(String opcion) {
        ArrayList<Suplementos> suplementos = new ArrayList<Suplementos>();

        if(opcion.equals("Proteins")){
            suplementos.add(new Suplementos("Whey 80", Textos.WHEY_80));
            suplementos.add(new Suplementos("Isolate whey", Textos.ISOLATE_WHEY));
            suplementos.add(new Suplementos("Casein", Textos.CASEINA));
            return suplementos;
        }else if (opcion.equals("Creatine")){
            suplementos.add(new Suplementos("Creatine monohydrate", Textos.CREATINA_MONOHIDRATO));
            suplementos.add(new Suplementos("Creatine hydrochloride", Textos.CLORHIDRATO_DE_CREATINA));
            suplementos.add(new Suplementos("Creatine ethyl ester", Textos.ETER_ETILICO_DE_CREATINA));
            suplementos.add(new Suplementos("Buffered creatine (Kre-Alkalyn)", Textos.CREATINA_TAMPONADA));
            suplementos.add(new Suplementos("Magnesium chelate creatine", Textos.CREATINA_QUELATO));
            suplementos.add(new Suplementos("Liquid creatine", Textos.CREATINA_LIQUIDA));

            return suplementos;
        }
        else if (opcion.equals("Amino acids")){
            suplementos.add(new Suplementos("Essential amino acids (EAA)", Textos.AMINOACIDOS_ESENCIALES));
            suplementos.add(new Suplementos("Branched-chain amino acids (BCAA)", Textos.AMINOACIDOS_RAMIFICADOS));
            suplementos.add(new Suplementos("Isoleucine", Textos.ISOLEUCINA));
            suplementos.add(new Suplementos("Valine", Textos.VALINA));
            suplementos.add(new Suplementos("Lysine", Textos.LISINA));
            suplementos.add(new Suplementos("Methionine", Textos.METIONINA));
            suplementos.add(new Suplementos("Phenylalanine", Textos.FENILANINA));
            suplementos.add(new Suplementos("Threonine", Textos.TREONINA));
            suplementos.add(new Suplementos("Tryptophan", Textos.TRIPTOFANO));
            suplementos.add(new Suplementos("Histidine", Textos.HISTIDINA));

            return suplementos;
        }else if (opcion.equals("Pre-workouts")){
            suplementos.add(new Suplementos("Citrulline", Textos.CITRULINA));
            suplementos.add(new Suplementos("Malate", Textos.MALATO));
            suplementos.add(new Suplementos("Beta-Alanine", Textos.BETA_ALANINA));
            suplementos.add(new Suplementos("Caffeine", Textos.CAFEINA));
            suplementos.add(new Suplementos("Combined pre-workouts", Textos.COMBINADOS));

            return suplementos;
        }else if (opcion.equals("Vitamins")){
            suplementos.add(new Suplementos("Vitamin B complex", Textos.COMPLEJO_VITIMINICO_B));
            suplementos.add(new Suplementos("Vitamin C", Textos.VITAMINA_C));
            suplementos.add(new Suplementos("Vitamin D", Textos.VITAMINA_D));
            suplementos.add(new Suplementos("Omega-3", Textos.OMEGA_3));
            suplementos.add(new Suplementos("Magnesium", Textos.MAGNESIO));
            suplementos.add(new Suplementos("Calcium", Textos.CALCIO));
            suplementos.add(new Suplementos("Multivitamins", Textos.MULTIVITAMINICOS));

            return suplementos;
        }
        else if (opcion.equals("Sleep improvement")){
            suplementos.add(new Suplementos("GABA", Textos.GABA));
            suplementos.add(new Suplementos("Melatonin", Textos.MELATONINA));
            suplementos.add(new Suplementos("Tryptophan", Textos.TRIPTOFANO));
            suplementos.add(new Suplementos("Ashwagandha", Textos.ASHWAGANDHA));

            return suplementos;
        }
        else{
            return suplementos;
        }

    }
}
