package com.example.temple_body.Ejercicios;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
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
    private RecyclerView recycleEjercicios;
    private EjercicioAdapter adapter;
    private Spinner spinnerEjercicios;

    private FragmentContainerView detalles;
    private View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ejercicios, container, false);

        mainView = view;

        recycleEjercicios = view.findViewById(R.id.recycleEjercicios);
        spinnerEjercicios = view.findViewById(R.id.spinnerEjercicios);
        recycleEjercicios.setLayoutManager(new LinearLayoutManager(getContext()));
        detalles = view.findViewById(R.id.fragmentDetalleEjercicio);

        // Agregar listener al Spinner para detectar cambios de selección
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
        Call<JsonArray> call;
        if (spinnerEjercicios.getSelectedItem().toString().equals("Abdominales")) {
            call = ServiceEjercicios.getAPI().getEjercicio("abdominals");
        } else if (spinnerEjercicios.getSelectedItem().toString().equals("Abductores")) {
            call = ServiceEjercicios.getAPI().getEjercicio("abductors");
        } else if (spinnerEjercicios.getSelectedItem().toString().equals("Adductores")) {
            call = ServiceEjercicios.getAPI().getEjercicio("adductors");
        } else if(spinnerEjercicios.getSelectedItem().toString().equals("Biceps")) {
            call = ServiceEjercicios.getAPI().getEjercicio("biceps");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Gemelos")) {
            call = ServiceEjercicios.getAPI().getEjercicio("calves");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Pecho")) {
            call = ServiceEjercicios.getAPI().getEjercicio("chest");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Antebrazos")) {
            call = ServiceEjercicios.getAPI().getEjercicio("forearms");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Gluteos")) {
            call = ServiceEjercicios.getAPI().getEjercicio("glutes");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Femoral")) {
            call = ServiceEjercicios.getAPI().getEjercicio("hamstrings");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Dorsales")) {
            call = ServiceEjercicios.getAPI().getEjercicio("lats");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Lumbar")) {
            call = ServiceEjercicios.getAPI().getEjercicio("lower_back");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Espalda Media")) {
            call = ServiceEjercicios.getAPI().getEjercicio("middle_back");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Cuello")) {
            call = ServiceEjercicios.getAPI().getEjercicio("neck");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Cuadriceps")) {
            call = ServiceEjercicios.getAPI().getEjercicio("quadriceps");
        }else if(spinnerEjercicios.getSelectedItem().toString().equals("Trapecios")) {
            call = ServiceEjercicios.getAPI().getEjercicio("traps");
        }else {
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
                                openDetalleEjercicioFragment(ejercicio);
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

    private void openDetalleEjercicioFragment(Ejercicio ejercicio) {
        DetalleEjercicioFragment detalleFragment = DetalleEjercicioFragment.newInstance(ejercicio.getName());
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentDetalleEjercicio, detalleFragment);
        transaction.addToBackStack("detalleEjercicio");
        transaction.commit();
        detalles.setVisibility(View.VISIBLE);
        View mainView = getView();
        if (mainView != null) {
            mainView.setClickable(false);
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

