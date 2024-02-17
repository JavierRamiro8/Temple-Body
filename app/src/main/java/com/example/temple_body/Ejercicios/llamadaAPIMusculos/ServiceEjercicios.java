package com.example.temple_body.Ejercicios.llamadaAPIMusculos;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceEjercicios {
    private static ServiceEjercicios ejercicios;
    private static EjerciciosAPI API;

    private ServiceEjercicios(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.api-ninjas.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API=retrofit.create(EjerciciosAPI.class);
    }
    public static EjerciciosAPI getAPI(){
        if (API == null) {
            ejercicios = new ServiceEjercicios();
        }
        return API;
    }


    public static ServiceEjercicios getEjercicios(){
        if (ejercicios ==null){
            ejercicios =new ServiceEjercicios();
        }
        return ejercicios;
    }
}
