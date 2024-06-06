package com.example.temple_body.Ejercicios;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.Ejercicios.llamadaAPIMusculos.Ejercicio;
import com.example.temple_body.Ejercicios.llamadaAPIMusculos.ServiceEjercicios;
import com.example.temple_body.R;
import com.google.firebase.Firebase;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EjerciciosFragment extends Fragment {
    private RecyclerView recycleEjercicios;
    private EjercicioAdapter adapter;
    private Spinner spinnerEjercicios;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ejercicios, container, false);

        recycleEjercicios = view.findViewById(R.id.recycleEjercicios);
        spinnerEjercicios = view.findViewById(R.id.spinnerEjercicios);
        recycleEjercicios.setLayoutManager(new LinearLayoutManager(getContext()));
        spinnerEjercicios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerEjercicios.getSelectedItemPosition() != 0) {
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
        Call<JsonArray> call = null;
        if (spinnerEjercicios.getSelectedItem().toString().equals("Abdominals")) {
            call = ServiceEjercicios.getAPI().getEjercicio("abdominals");
        } else if (spinnerEjercicios.getSelectedItem().toString().equals("Abductors")) {
            call = ServiceEjercicios.getAPI().getEjercicio("abductors");
        } else if (spinnerEjercicios.getSelectedItem().toString().equals("Adductors")) {
            call = ServiceEjercicios.getAPI().getEjercicio("adductors");
        } else if(spinnerEjercicios.getSelectedItem().toString().equals("Biceps")) {
            call = ServiceEjercicios.getAPI().getEjercicio("biceps");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Calves")) {
            call = ServiceEjercicios.getAPI().getEjercicio("calves");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Chest")) {
            call = ServiceEjercicios.getAPI().getEjercicio("chest");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Forearms")) {
            call = ServiceEjercicios.getAPI().getEjercicio("forearms");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Glutes")) {
            call = ServiceEjercicios.getAPI().getEjercicio("glutes");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Hamstrings")) {
            call = ServiceEjercicios.getAPI().getEjercicio("hamstrings");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Lats")) {
            call = ServiceEjercicios.getAPI().getEjercicio("lats");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Lower back")) {
            call = ServiceEjercicios.getAPI().getEjercicio("lower_back");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Middle back")) {
            call = ServiceEjercicios.getAPI().getEjercicio("middle_back");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Cuello")) {
            call = ServiceEjercicios.getAPI().getEjercicio("neck");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Quadriceps")) {
            call = ServiceEjercicios.getAPI().getEjercicio("quadriceps");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Traps")) {
            call = ServiceEjercicios.getAPI().getEjercicio("traps");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Triceps")){
            call = ServiceEjercicios.getAPI().getEjercicio("triceps");
        }

        if (call != null) {
            call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        JsonArray jsonArray = response.body();
                        List<Ejercicio> listaEjercicios = parseJson(jsonArray);
                        adapter = new EjercicioAdapter(listaEjercicios);
                        adapter.setOnItemClickListener(new EjercicioAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Ejercicio ejercicio) {
                                openDetalleEjercicioFragment(ejercicio.getName());
                            }
                        });
                        recycleEjercicios.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    Log.e("API Call Failure", "Error al obtener ejercicios", t);
                }
            });
        }
    }

    private void openDetalleEjercicioFragment(String ejercicio) {
        Bundle bundle=new Bundle();
        bundle.putString("nombreEjercicio",ejercicio);
        NavController nav= NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_ejerciciosFragment_to_detalleEjercicio3,bundle);
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

