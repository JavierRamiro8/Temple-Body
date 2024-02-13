package com.example.temple_body.Ejercicios;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleEjercicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class DetalleEjercicioFragment extends Fragment {

    private static String nombreEjercicio = "name";
    private EditText peso;

    private EditText repeticiones;
    private EditText series;


    private String ejercicio;
    private TextView TituloNombreEjercicio;

    public DetalleEjercicioFragment() {
        // Constructor público vacío requerido
    }

    public static DetalleEjercicioFragment newInstance(String exerciseName) {
        DetalleEjercicioFragment fragment = new DetalleEjercicioFragment();
        Bundle args = new Bundle();
        args.putString(nombreEjercicio, exerciseName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ejercicio = getArguments().getString(nombreEjercicio);
            TituloNombreEjercicio=

        }
    }

}

