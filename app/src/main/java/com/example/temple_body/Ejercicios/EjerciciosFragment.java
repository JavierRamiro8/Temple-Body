package com.example.temple_body.Ejercicios;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EjerciciosFragment extends Fragment {
    private RecyclerView recyclerView;
    private EjercicioAdapter adapter;
    private Spinner spinnerEjercicios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ejercicios, container, false);

        recyclerView = view.findViewById(R.id.recycleEjercicios);
        spinnerEjercicios = view.findViewById(R.id.spinnerEjercicios);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Agregar listener al Spinner para detectar cambios de selección
        spinnerEjercicios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerEjercicios.getSelectedItemPosition()!=0){
                    getEjercicios();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }


    private void getEjercicios() {
        Call<JsonArray> call;
        if (spinnerEjercicios.getSelectedItem().toString().equals("Biceps")) {
            call = ServiceEjercicios.getAPI().getEjercicio("biceps");
        } else if (spinnerEjercicios.getSelectedItem().toString().equals("Pecho")) {
            call = ServiceEjercicios.getAPI().getEjercicio("chest");
        } else if (spinnerEjercicios.getSelectedItem().toString().equals("brazos")) {
            call = ServiceEjercicios.getAPI().getEjercicio("forearms");
        } else{
            call = ServiceEjercicios.getAPI().getEjercicio("abdominals");
        }

        // Verificar que la llamada no sea nula
        if (call != null) {
            call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        JsonArray jsonArray = response.body();
                        List<Ejercicio> listaEjercicios = parseJson(jsonArray);
                        adapter = new EjercicioAdapter(listaEjercicios);
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    Log.e("API Call Failure", "Error al obtener ejercicios", t);
                }
            });
        }
    }


    private List<Ejercicio> parseJson(JsonArray jsonArray) {
        List<Ejercicio> listaEjercicios = new ArrayList<>();

        for (JsonElement jsonElement : jsonArray) {
            JsonObject ejercicioObject = jsonElement.getAsJsonObject();
            String name = ejercicioObject.get("name").getAsString();
            String muscle = ejercicioObject.get("muscle").getAsString();
            String difficulty = ejercicioObject.get("difficulty").getAsString();

            Ejercicio ejercicio = new Ejercicio(name, muscle, difficulty);
            listaEjercicios.add(ejercicio);
        }

        return listaEjercicios;
    }
}
