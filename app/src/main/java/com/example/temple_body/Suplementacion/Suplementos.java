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
    public static ArrayList<Suplementos> generador(String opcion) {
        ArrayList<Suplementos> suplementos = new ArrayList<Suplementos>();
        if(opcion.equals("Proteinas")){
            suplementos.add(new Suplementos("Whey 80", "El Concentrado Whey Protein 80 ha sido desarrollado sin hacer concesión alguna, utilizando exclusivamente el mejor concentrado de proteína de suero, ofreciendo un descomunal 82% de contenido de proteínas"));
            suplementos.add(new Suplementos("Isolate whey", "La proteína aislada es una fuente de este macronutriente, que se caracteriza por contar con una pureza entorno al 90%. El contenido de grasas e hidratos de carbono que posee es prácticamente inexistente."));
            suplementos.add(new Suplementos("Caseina", "La caseína es una proteína de un alto valor biológico que contiene minerales importantes como el calcio y el fósforo, así como los 9 aminoácidos esenciales que tu cuerpo no puede producir por sí mismo y que necesita para ayudar a construir músculo, como la leucina, isoleucina y valina."));
            return suplementos;
        }else if (opcion.equals("Creatina")){
            suplementos.add(new Suplementos("Creatina monohidrato", "Compuesto por una molécula de creatina unida a una molécula de agua, es el tipo más común de suplemento de creatina para mejorar el rendimiento.\n" +
                    "\n" +
                    "Esta forma de creatina, no solo es la más estudiada, también es la que tiene más beneficios para la salud, ya que es la forma que se ha utilizado en la mayoría de los estudios realizados.\n"+
                    "Esta es la creatina más recomendada por nutricionistas."));
            suplementos.add(new Suplementos("Clorhidrato de creatina", "Este tipo de creatina está molecularmente unida con ácido clorhídrico, diseñado para mejorar sus tasas de absorción global. También conocida como creatina HCI, la creatina clorhidrato ganó popularidad gracias a los informes sobre su mayor solubilidad en agua."));
            suplementos.add(new Suplementos("Éster etílico de creatina", "La creatina está unida a sales de éster en esta forma, que está diseñada para hacer que la creatina se absorba más fácilmente en el cuerpo. Sin embargo, a pesar de sus buenas intenciones, un estudio que comparó el éster etílico con el monohidrato descubrió que el éster etílico puede ser en realidad peor para aumentar el contenido de creatina en la sangre y los músculos."));
            suplementos.add(new Suplementos("Creatina tamponada (Kre-Alkalyn)", "Este tipo de creatina tiene un pH más alto que la mayoría de los monohidratos de creatina normales, lo que da lugar a una forma tamponada. Esto se genera por los fabricantes de suplementos que han añadido un polvo alcalino, tratando de mejorar la estabilidad de la creatina en el estómago."));
            suplementos.add(new Suplementos("Creatina de quelato de magnesio", "Este tipo de creatina se vende a menudo bajo el nombre de MagnaPower y es creatina que se ha unido con magnesio, mientras que la creatina monohidrato está unido a una molécula de agua. Sin embargo, una idea errónea muy extendida es que el quelato de magnesio se absorbe mejor que el monohidrato, pero las pruebas que lo corroboran son limitadas."));
            suplementos.add(new Suplementos("Creatina líquida", "La creatina líquida se refiere a las versiones de creatina listas para beber, en las que la creatina ya está disuelta en agua. Sin embargo, es importante señalar que, aunque limitadas, las investigaciones apuntan a que la creatina líquida puede ser menos eficaz que otras formas, como los polvos. La investigación demostró que la creatina puede descomponerse cuando permanece en líquido durante varios días seguidos, lo que la hace menos eficaz."));

            return suplementos;
        }else{
            return suplementos;
        }

    }
}
