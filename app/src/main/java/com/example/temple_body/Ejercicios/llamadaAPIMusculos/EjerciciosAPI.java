package com.example.temple_body.Ejercicios.llamadaAPIMusculos;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface EjerciciosAPI {
    @Headers({"X-Api-Key: vL4mPM3Y6nh8Ha90wN8Y9Q==YwgQ69NzQKvy0sxp"})
    @GET("/v1/exercises")
    public Call<JsonArray> getEjercicio(@Query("muscle")String musculo);
}
