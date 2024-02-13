package com.example.temple_body.Ejercicios;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temple_body.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
    private TextView tituloNombreEjercicio;
    private String ejercicio;
    private Button generar;
    private Button salir;
    private MutableLiveData<List<Historial>> liveHistorial = new MutableLiveData<>();

    private TextView resultado;

    public DetalleEjercicioFragment() {
    }

    public static DetalleEjercicioFragment newInstance(String exerciseName) {
        DetalleEjercicioFragment fragment = new DetalleEjercicioFragment();
        Bundle args = new Bundle();
        args.putString(nombreEjercicio, exerciseName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_ejercicio, container, false);
        peso = view.findViewById(R.id.EDPeso);
        repeticiones = view.findViewById(R.id.EDRepeticiones);
        series = view.findViewById(R.id.EDSeries);
        tituloNombreEjercicio = view.findViewById(R.id.TVNombreEjercicio);
        resultado = view.findViewById(R.id.TVResultado);
        generar = view.findViewById(R.id.BTGenerar);
        salir = view.findViewById(R.id.BTSalir);
        if (getArguments() != null) {
            ejercicio = getArguments().getString(nombreEjercicio);
            tituloNombreEjercicio.setText(ejercicio);
        }
        generar.setOnClickListener(v -> {
            if (peso.getText().equals(" ") | repeticiones.getText().equals(" ") | series.getText().equals(" ")) {
                peso.setText(" ");
                repeticiones.setText(" ");
                series.setText(" ");
            } else {
                agregarHistorial(Integer.valueOf(peso.getText().toString()), Integer.valueOf(repeticiones.getText().toString()), Integer.valueOf(series.getText().toString()));
            }
        });
        liveHistorial.observe(getViewLifecycleOwner(), new Observer<List<Historial>>() {
            @Override
            public void onChanged(List<Historial> historial) {
                StringBuilder historialText = new StringBuilder();
                for (Historial ejercicio : historial) {
                    historialText.append("Peso: ").append(ejercicio.getPeso()).append(" Repeticiones: ").append(ejercicio.getRepeticiones()).append(" Series: ").append(ejercicio.getSeries()).append("\n");
                }
                resultado.setText(historialText.toString());
            }
        });
        salir.setOnClickListener(v -> {
            getParentFragmentManager().popBackStackImmediate();
        });

        return view;
    }

    public void agregarHistorial(int peso, int repeticiones, int series) {
        List<Historial> listaHistorial = liveHistorial.getValue();
        if (listaHistorial == null) {
            listaHistorial = new ArrayList<>();
        }
        listaHistorial.add(new Historial(peso, repeticiones, series));
        liveHistorial.setValue(listaHistorial);
    }
    @Override
    public void onResume() {
        super.onResume();
        // Oculta el fragmento de detalles al volver a él
        requireView().setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Oculta el fragmento de detalles al salir de él
        requireView().setVisibility(View.INVISIBLE);
    }
}

