package com.example.temple_body.Ejercicios.llamadaAPINombre;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceNombreEjercicios {
    private static ServiceNombreEjercicios ejercicios;
    private static EjerciciosAPIPorNombre API;

    private ServiceNombreEjercicios(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.api-ninjas.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API=retrofit.create(EjerciciosAPIPorNombre.class);
    }
    public static EjerciciosAPIPorNombre getAPIPorNombre(){
        if (API == null) {
            ejercicios = new ServiceNombreEjercicios();
        }
        return API;
    }


    public static ServiceNombreEjercicios getEjerciciosPorNombre(){
        if (ejercicios ==null){
            ejercicios =new ServiceNombreEjercicios();
        }
        return ejercicios;
    }
}
