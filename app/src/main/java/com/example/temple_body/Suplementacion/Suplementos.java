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
                    "Esta forma de creatina, no solo es la más estudiada, también es la que tiene más beneficios para la salud, ya que es la forma que se ha utilizado en la mayoría de los estudios realizados.\n"+
                    "Esta es la creatina más recomendada por nutricionistas."));
            suplementos.add(new Suplementos("Clorhidrato de creatina", "Este tipo de creatina está molecularmente unida con ácido clorhídrico, diseñado para mejorar sus tasas de absorción global. También conocida como creatina HCI, la creatina clorhidrato ganó popularidad gracias a los informes sobre su mayor solubilidad en agua."));
            suplementos.add(new Suplementos("Éster etílico de creatina", "La creatina está unida a sales de éster en esta forma, que está diseñada para hacer que la creatina se absorba más fácilmente en el cuerpo. Sin embargo, a pesar de sus buenas intenciones, un estudio que comparó el éster etílico con el monohidrato descubrió que el éster etílico puede ser en realidad peor para aumentar el contenido de creatina en la sangre y los músculos."));
            suplementos.add(new Suplementos("Creatina tamponada (Kre-Alkalyn)", "Este tipo de creatina tiene un pH más alto que la mayoría de los monohidratos de creatina normales, lo que da lugar a una forma tamponada. Esto se genera por los fabricantes de suplementos que han añadido un polvo alcalino, tratando de mejorar la estabilidad de la creatina en el estómago."));
            suplementos.add(new Suplementos("Creatina de quelato de magnesio", "Este tipo de creatina se vende a menudo bajo el nombre de MagnaPower y es creatina que se ha unido con magnesio, mientras que la creatina monohidrato está unido a una molécula de agua. Sin embargo, una idea errónea muy extendida es que el quelato de magnesio se absorbe mejor que el monohidrato, pero las pruebas que lo corroboran son limitadas."));
            suplementos.add(new Suplementos("Creatina líquida", "La creatina líquida se refiere a las versiones de creatina listas para beber, en las que la creatina ya está disuelta en agua. Sin embargo, es importante señalar que, aunque limitadas, las investigaciones apuntan a que la creatina líquida puede ser menos eficaz que otras formas, como los polvos. La investigación demostró que la creatina puede descomponerse cuando permanece en líquido durante varios días seguidos, lo que la hace menos eficaz."));

            return suplementos;
        }
        else if (opcion.equals("Aminoacidos")){
            suplementos.add(new Suplementos("Aminoácidos esenciales ( EAA ) ", "Son necesarios con el fin de ayudar a construir nuevo tejido muscular y ayuda en la reparación celular que se produce cuando los músculos se están recuperando. Ellos son ”esenciales” porque el cuerpo no puede producirlos. Son los 3 aminoácidos esenciales más importantes: leucina, isoleucina y valina."));
            suplementos.add(new Suplementos("Aminoacidos ramificados ( BCAA )", "Son conocidos por ayudar a mejorar la síntesis de proteínas musculares, lo que puede ayudar a aumentar la masa muscular y mejorar la fuerza. También se ha demostrado que pueden ayudar a reducir la fatiga muscular y mejorar el rendimiento durante el ejercicio de resistencia. Los 9 aminoácidos esenciales son: histidina, isoleucina, leucina, lisina, metionina, fenilalanina, treonina, triptófano y valina."));
            suplementos.add(new Suplementos("Isoleucina", "Ayuda a regenerar el tejido muscular, así como a regular los niveles de azúcar en la sangre, e interviene en la formación de hemoglobina. Está presente en los huevos, los lácteos, el pollo, el salmón, las algas marinas, o las nueces, entre otros alimentos."));
            suplementos.add(new Suplementos("Leucina", "Reduce la degradación del tejido muscular, al incrementar la síntesis de proteína,  y aporta energía a los músculos. Se puede obtener de los mismos alimentos que presentan la Isoleucina (carnes, pescados, lácteos,etc.)."));
            suplementos.add(new Suplementos("Valina", "Forma parte del tejido muscular, interviene en el metabolismo de los mismos y en la reparación de los tejidos. Se usa en el proceso de provisión de energía a las células musculares y, además, se encarga de enviar los mensajes químicos al sistema nervioso. Está presente en los plátanos, los frutos rojos, el chocolate, el requesón, así como en la carne de vacuno y el pescado."));
            suplementos.add(new Suplementos("Lisina", "Tiene el poder de preservar el tejido muscular, y es uno de los aminoácidos responsables de la síntesis de la carnitina, cuya ingesta favorece la utilización de la grasa para ser transformada en energía. ¿En qué alimentos podemos encontrar la Lisina? En las legumbres, los frutes secos, los lácteos, huevos, carnes y pescados, etc."));
            suplementos.add(new Suplementos("Metionina", "Participa directamente en la producción de proteína. Y es otro de los aminoácidos esenciales que participan en la síntesis de la carnitina. Las semillas de sésamo son una fuente con altos niveles de metionina, al igual que la carne de vacuno, las espinacas o el maíz, entre otros alimentos."));
            suplementos.add(new Suplementos("Fenilalanina", "Es otro de los aminoácidos esenciales encargado de la producción de proteínas, y presente en muchos psicoactivos, como la adrenalina o la dopamina. Está presente en la soja, los espárragos, las lentejas, las carnes de aves y el cerdo, productos lácteos, los huevo, pescados como el salmón."));
            suplementos.add(new Suplementos("Treonina", "¿Sabes que fue uno de los últimos aminoácidos en ser descubierto? La treonina es importante para el crecimiento muscular, e interviene en la sintesis de enzimas digestivas, así como en la formación del colágeno o la elastina. Puedes obtener la treonina al ingerir: requesón, semillas de sésamo, aves, salmón o los huevos, entre otros."));
            suplementos.add(new Suplementos("Triptófano", "Posee una función antidepresiva, ya que interviene en la gestación de las moléculas de la serotonina, la hormona que produce bienestar. Uno de los alimentos que contiene triptofano es el chocolate, ¡¿ahora entiendes por qué esta manjar nos hace tan felices a muchos?!\n" +
                    "Y también está presente en el huevo, la leche, la avena, las almendras, los dátiles, los garbanzos, las pipas de girasol o calabaza, los cacahuetes, los plátanos, las carnes rojas, el pescado, el pollo, entre otros."));
            suplementos.add(new Suplementos("Histidina", "Contribuye en el mantenimiento y el desarrollo de los tejidos. Los alimentos proteicos como la carne, el pescado y los huevos contienen este aminoácido."));

            return suplementos;
        }else if (opcion.equals("Pre-entrenos")){
            suplementos.add(new Suplementos("Citrulina", "La citrulina es un aminoácido no esencial que influye en el ciclo de la urea y en la producción de óxido nítrico, que actúa como vasodilatador para mejorar la circulación de la sangre (y el suministro de oxígeno) por todo el cuerpo."));
            suplementos.add(new Suplementos("Malato", "El malato es un componente esencial que influye en la producción de energía ayudando a generar ATP, que es la energía que utilizan los músculos para los movimientos de contracción."));
            suplementos.add(new Suplementos("Beta-Alanina", "La beta-alanina es un aminoácido no esencial sintetizado en el hígado. Este aminoácido junto a la histidina forma moléculas de carnosina. De esta forma, con la suplementación continuada de beta-alanina podemos aumentar los niveles de en nuestros músculos con sus consecuentes beneficios."));
            suplementos.add(new Suplementos("Cafeína", "La cafeína es una sustancia que se encuentra en ciertas plantas. También se puede producir de manera artificial (sintéticamente) y agregarse a los productos alimenticios. Es un estimulante del sistema nervioso central y un diurético (sustancia que le ayuda al cuerpo a eliminar líquidos)."));
            suplementos.add(new Suplementos("Combinados", "La mayoria de pre-entrenos pueden estar ya creados, con la combinacion de los diferentes tipos de sustancias estimulantes, energizantes, o que ayuden al aumento de resistencia o fuerza. Estos pre-entrenos ya dependerán de la combinacion que mas desees encontrar en ellos, en cada marca tendrán los suyos propios."));

            return suplementos;
        }else if (opcion.equals("Vitaminas")){
            suplementos.add(new Suplementos("Complejo vitaminico B", "En conjunto, mantienen la salud cerebral, la salud cardíaca, la formación del ADN y la regulación de la energía. Además, se ha comprobado que previenen las migrañas, evitan la reaparición de algunos tipos de cáncer de piel e incluso pueden retardar la progresión de una devastadora enfermedad neurodegenerativa."));
            suplementos.add(new Suplementos("Vitamina C", "La vitamina C se necesita para el crecimiento y reparación de tejidos en todas las partes del cuerpo. Se utiliza para: Formar una proteína importante llamada colágeno, utilizada para producir la piel, los tendones, los ligamentos y los vasos sanguíneos. Sanar heridas y formar tejido cicatricial."));
            suplementos.add(new Suplementos("Vitamina D", "La vitamina D ayuda al cuerpo a usar el calcio y el fósforo para fortificar los huesos y los dientes. Es soluble en grasas (se disuelve en grasas y aceites) y se encuentra en los peces grasos, las yemas de huevos y los productos lácteos. La piel también produce vitamina D cuando se expone a la luz del sol."));
            suplementos.add(new Suplementos("Omega-3", "Los ácidos grasos omega-3 son un tipo de grasa poliinsaturada. Necesitamos estas grasas para fortalecer las neuronas y para otras funciones importantes. Estos ácidos ayudan a mantener el corazón sano y protegido contra un accidente cerebrovascular."));
            suplementos.add(new Suplementos("Magnesio", "Ayuda a mantener el funcionamiento normal de músculos y nervios, brinda soporte a un sistema inmunitario saludable, mantiene constantes los latidos del corazón y ayuda a que los huesos permanezcan fuertes. También ayuda a ajustar los niveles de glucosa en la sangre. Ayuda en la producción de energía y proteína."));
            suplementos.add(new Suplementos("Calcio", "El calcio es un mineral que el cuerpo necesita para formar y mantener huesos fuertes y llevar a cabo muchas funciones importantes. El calcio es el mineral más abundante en el organismo. Casi todo el calcio se almacena en los huesos y los dientes, lo que les da estructura y rigidez."));
            suplementos.add(new Suplementos("Multivitamínicos", "Un multivitamínico es un suplemento dietético que aporta vitaminas, minerales y otros elementos nutricionales. Generalmente se disponen en forma de cápsulas, pastillas, polvos o líquidos. Están ahí para complementar las vitaminas y minerales que obtienes de los alimentos y acercarte un paso más a tus objetivos de salud y bienestar."));

            return suplementos;
        }
        else if (opcion.equals("Mejora del sueño")){
            suplementos.add(new Suplementos("GABA", "El GABA, o ácido gamma-aminobutírico, es un aminoácido y neurotransmisor que regula la excitabilidad cerebral. Es el principal neurotransmisor inhibidor, que evita que un impulso nervioso no se dispare e induce a la calma."));
            suplementos.add(new Suplementos("Melatonina", "La melatonina es uno de los suplementos más utilizados para mejorar la calidad del sueño y tratar problemas de insomnio. Es una hormona que produce de manera natural nuestro organismo que se encarga de regular el ciclo del sueño y los ritmos circadianos."));
            suplementos.add(new Suplementos("Triptófano", "El triptófano es un aminoácido esencial para la calidad del sueño, es decir, debe ser administrado al organismo mediante la dieta. Esta relacionado con la realización de diversas funciones como la síntesis de proteínas, regeneración del tejido muscular, mejorar el ánimo, reducir la depresión y favorecer un sueño reparador debido a que contribuye en la producción de determinadas hormonas y neurotransmisores."));
            suplementos.add(new Suplementos("Ashwagandha", "La ashwagandha es un suplemento nutricional que, en los últimos años, se ha empezado a emplear en al ámbito de la nutrición deportiva. Por lo general, la ashwagandha se utiliza en épocas en las que nos encontramos estresados o con ansiedad elevada."));

            return suplementos;
        }
        else{
            return suplementos;
        }

    }
}
