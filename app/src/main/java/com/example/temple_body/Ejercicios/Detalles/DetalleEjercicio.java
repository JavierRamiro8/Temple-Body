package com.example.temple_body.Ejercicios.Detalles;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.temple_body.Ejercicios.Detalles.RecycleDetalles.AdapterDetalleEjercicio;
import com.example.temple_body.Ejercicios.llamadaAPINombre.EjerciciosAPIPorNombre;
import com.example.temple_body.Ejercicios.llamadaAPINombre.ServiceNombreEjercicios;
import com.example.temple_body.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleEjercicio extends Fragment {

    private static final String nombreEjercicio = "nombreEjercicio";
    private AdapterDetalleEjercicio adapter;
    private RecyclerView descripcion;
    private TextView titulo;
    private Button historial, salida;
    private Bundle args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_ejercicio, container, false);
        descripcion = view.findViewById(R.id.descripcion);
        historial = view.findViewById(R.id.historial);
        salida = view.findViewById(R.id.salida);
        titulo = view.findViewById(R.id.TVTituloHistorial);
        args = getArguments();

        if (args != null && args.containsKey(nombreEjercicio)) {
            String getTituloEjercicio = args.getString(nombreEjercicio);
            titulo.setText(getTituloEjercicio);

            adapter = new AdapterDetalleEjercicio(getTituloEjercicio);

            //en las 2 lineas siguientes es para setear el adapter e IMPORTANTE: el LinearLayoutManager es para recoger el row, necesita saber de donde lo saca!!!
            descripcion.setAdapter(adapter);
            descripcion.setLayoutManager(new LinearLayoutManager(getContext()));
            apiDescripcion(getTituloEjercicio);
        } else {
            Log.e("DetalleEjercicio", "El argumento 'nombreEjercicio' es nulo o no está presente en los argumentos");
        }

        historial.setOnClickListener(v -> openHistorialFragment(titulo.getText().toString()));
        salida.setOnClickListener(v -> closeDetalleFragment());
        return view;
    }

    private void apiDescripcion(String nombreEjercicioSeleccionado) {
        EjerciciosAPIPorNombre apiInstrucciones = ServiceNombreEjercicios.getAPIPorNombre();
        Call<JsonArray> call = apiInstrucciones.getEjercicioPorNombre(nombreEjercicioSeleccionado);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    JsonArray getEjercicios = response.body();
                    if (getEjercicios != null && !getEjercicios.isJsonNull() && getEjercicios.size() > 0) {
                        JsonObject ejercicio = getEjercicios.get(0).getAsJsonObject();
                        if (ejercicio.has("instructions") && !ejercicio.get("instructions").isJsonNull()) {
                            String descripcionEjercicio = ejercicio.get("instructions").getAsString();
                            adapter.addDescripcion(descripcionEjercicio);//esto es para añadir la descripcion al RecycleView
                        } else {
                            Log.e("DetalleEjercicio", "No se encontraron instrucciones para este ejercicio");
                        }
                    } else {
                        Log.e("DetalleEjercicio", "La respuesta de la API está vacía o no contiene datos válidos");
                    }
                } else {
                    Log.e("DetalleEjercicio", "Error, No se pudo obtener la respuesta de la API");
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("DetalleEjercicio", "Error, No se pudo conectar con la API", t);
            }
        });
    }

    private void openHistorialFragment(String ejercicio) {
        Bundle bundle = new Bundle();
        bundle.putString("nombreEjercicio", ejercicio);
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_detalleEjercicio_to_historialEjercicioFragment3, bundle);
    }

    private void closeDetalleFragment() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_detalleEjercicio_to_ejerciciosFragment2);
    }
}

