package com.example.temple_body.Ejercicios.Detalles;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
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
    private static final String descripcionEjercicio = "descripcionEjercicio";
    private static final String URL_BUSQUEDA = "https://www.youtube.com/results?search_query=";
    private AdapterDetalleEjercicio adapter;
    private ScrollView descripcion;
    private TextView textoDescripcion;
    private TextView titulo;
    private Button historial, salida, video, verExplicacion;
    private Bundle args;
    Boolean textoOculto = true;
    String getTituloEjercicio = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_ejercicio, container, false);
        descripcion = view.findViewById(R.id.descripcion);
        textoDescripcion = view.findViewById(R.id.textoDescripcion);
        verExplicacion = view.findViewById(R.id.verExplicacion);
        historial = view.findViewById(R.id.historial);
        salida = view.findViewById(R.id.salida);
        titulo = view.findViewById(R.id.TVTituloHistorial);
        video = view.findViewById(R.id.video);
        args = getArguments();
        descripcion.getLayoutParams().height = 5;
        video.setOnClickListener(v -> {
            viajarReproductorVideo(titulo.getText().toString());
        });
        if (textoOculto) {
            textoDescripcion.setText("");
        }
        if (args != null && args.containsKey(nombreEjercicio)) {
            getTituloEjercicio = args.getString(nombreEjercicio);
            titulo.setText(getTituloEjercicio);
        }

        verExplicacion.setOnClickListener(v -> {
            if (textoOculto) {
                if (args != null && args.containsKey(nombreEjercicio)) {
                    descripcion.getLayoutParams().height = 500;
                    descripcion.requestLayout();
                    apiDescripcion(getTituloEjercicio);
                } else {
                    getTituloEjercicio = null;
                    Log.e("DetalleEjercicio", "El argumento 'nombreEjercicio' es nulo o no está presente en los argumentos");
                }
                textoOculto = false;
            } else {
                descripcion.getLayoutParams().height = 5;
                descripcion.requestLayout();
                textoDescripcion.setText("");
                textoOculto = true;
            }
        });
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
                            textoDescripcion.setText(descripcionEjercicio);//esto es para añadir la descripcion al RecycleView
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

    private void viajarReproductorVideo(String ejercicio) {
        Bundle bundle = new Bundle();
        bundle.putString("nombreEjercicio", ejercicio);
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_detalleEjercicio_to_reproductorVideo, bundle);
    }

    private void closeDetalleFragment() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_detalleEjercicio_to_ejerciciosFragment2);
    }
}