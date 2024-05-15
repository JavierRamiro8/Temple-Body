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
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public static ArrayList<Suplementos> generador(String opcion) {
        ArrayList<Suplementos> suplementos = new ArrayList<Suplementos>();

        if(opcion.equals("Proteinas")){
            suplementos.add(new Suplementos("Whey 80", Textos.WHEY_80));
            suplementos.add(new Suplementos("Isolate whey", Textos.ISOLATE_WHEY));
            suplementos.add(new Suplementos("Caseina", Textos.CASEINA));
            return suplementos;
        }else if (opcion.equals("Creatina")){
            suplementos.add(new Suplementos("Creatina monohidrato", Textos.CREATINA_MONOHIDRATO));
            suplementos.add(new Suplementos("Clorhidrato de creatina", Textos.CLORHIDRATO_DE_CREATINA));
            suplementos.add(new Suplementos("Éter etílico de creatina", Textos.ETER_ETILICO_DE_CREATINA));
            suplementos.add(new Suplementos("Creatina tamponada (Kre-Alkalyn)", Textos.CREATINA_TAMPONADA));
            suplementos.add(new Suplementos("Creatina de quelato de magnesio", Textos.CREATINA_QUELATO));
            suplementos.add(new Suplementos("Creatina líquida", Textos.CREATINA_LIQUIDA));

            return suplementos;
        }
        else if (opcion.equals("Aminoacidos")){
            suplementos.add(new Suplementos("Aminoácidos esenciales ( EAA ) ", Textos.AMINOACIDOS_ESENCIALES));
            suplementos.add(new Suplementos("Aminoacidos ramificados ( BCAA )", Textos.AMINOACIDOS_RAMIFICADOS));
            suplementos.add(new Suplementos("Leucina", "Reduce la degradación del tejido muscular, al incrementar la síntesis de proteína,  y aporta energía a los músculos. Se puede obtener de los mismos alimentos que presentan la Isoleucina (carnes, pescados, lácteos,etc.)."));
            suplementos.add(new Suplementos("Isoleucina", Textos.ISOLEUCINA));
            suplementos.add(new Suplementos("Valina", Textos.VALINA));
            suplementos.add(new Suplementos("Lisina", Textos.LISINA));
            suplementos.add(new Suplementos("Metionina", Textos.METIONINA));
            suplementos.add(new Suplementos("Fenilalanina", Textos.FENILANINA));
            suplementos.add(new Suplementos("Treonina", Textos.TREONINA));
            suplementos.add(new Suplementos("Triptófano", Textos.TRIPTOFANO));
            suplementos.add(new Suplementos("Histidina", Textos.HISTIDINA));

            return suplementos;
        }else if (opcion.equals("Pre-entrenos")){
            suplementos.add(new Suplementos("Citrulina", Textos.CITRULINA));
            suplementos.add(new Suplementos("Malato", Textos.MALATO));
            suplementos.add(new Suplementos("Beta-Alanina", Textos.BETA_ALANINA));
            suplementos.add(new Suplementos("Cafeína", Textos.CAFEINA));
            suplementos.add(new Suplementos("Pre-entrenos combinados", Textos.COMBINADOS));

            return suplementos;
        }else if (opcion.equals("Vitaminas")){
            suplementos.add(new Suplementos("Complejo vitaminico B", Textos.COMPLEJO_VITIMINICO_B));
            suplementos.add(new Suplementos("Vitamina C", Textos.VITAMINA_C));
            suplementos.add(new Suplementos("Vitamina D", Textos.VITAMINA_D));
            suplementos.add(new Suplementos("Omega-3", Textos.OMEGA_3));
            suplementos.add(new Suplementos("Magnesio", Textos.MAGNESIO));
            suplementos.add(new Suplementos("Calcio", Textos.CALCIO));
            suplementos.add(new Suplementos("Multivitamínicos", Textos.MULTIVITAMINICOS));

            return suplementos;
        }
        else if (opcion.equals("Mejora del sueño")){
            suplementos.add(new Suplementos("GABA", Textos.GABA));
            suplementos.add(new Suplementos("Melatonina", Textos.MELATONINA));
            suplementos.add(new Suplementos("Triptófano", Textos.TRIPTOFANO));
            suplementos.add(new Suplementos("Ashwagandha", Textos.ASHWAGANDHA));

            return suplementos;
        }
        else{
            return suplementos;
        }

    }
}
