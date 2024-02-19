package com.example.temple_body.Ejercicios.Historial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.temple_body.Ejercicios.Historial.RecycleHistorial.AdapterHistorialEjercicio;
import com.example.temple_body.Ejercicios.Historial.RecycleHistorial.Historial;
import com.example.temple_body.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistorialEjercicioFragment extends Fragment {

    private static String nombreEjercicio = "nombreEjercicio";
    private EditText peso;
    private EditText repeticiones;
    private EditText series;
    private TextView tituloNombreEjercicio;
    private Button datepicker,generar,salir;
    private String fecha;
    private MutableLiveData<List<Historial>> liveHistorial = new MutableLiveData<>();
    private RecyclerView resultado;


    public HistorialEjercicioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial_ejercicio, container, false);
        peso = view.findViewById(R.id.EDPeso);
        repeticiones = view.findViewById(R.id.EDRepeticiones);
        series = view.findViewById(R.id.EDSeries);
        tituloNombreEjercicio = view.findViewById(R.id.TVTituloHistorial);
        resultado = view.findViewById(R.id.recycleHistorial);
        generar = view.findViewById(R.id.BTGenerar);
        salir = view.findViewById(R.id.BTSalir);
        datepicker=view.findViewById(R.id.datePicker);
        Bundle args=getArguments();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        resultado.setLayoutManager(layoutManager);
        AdapterHistorialEjercicio adapter=new AdapterHistorialEjercicio(new ArrayList<>());
        resultado.setAdapter(adapter);
        if (args != null && args.containsKey(nombreEjercicio)) {
            String getTituloEjercicio = args.getString(nombreEjercicio);
            tituloNombreEjercicio.setText(getTituloEjercicio);
        }
        generar.setOnClickListener(v -> {
            if(peso.getText().toString().isEmpty()) {
                peso.setError("Por favor Introduzca Peso");

            }else if(repeticiones.getText().toString().isEmpty()) {
                repeticiones.setError("Por favor Introduzca Repeticiones");

            }else if(series.getText().toString().isEmpty()) {
                series.setError("Por favor Introduzca las series");
            }else if(datepicker.getText().equals("Selecciona Fecha")){
                datepicker.setError("Por favor Introduzca la fecha");
            } else {
                agregarHistorial(Integer.valueOf(peso.getText().toString()), Integer.valueOf(repeticiones.getText().toString()), Integer.valueOf(series.getText().toString()),fecha);
            }
        });
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker<Long> materialDatePicker=MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Selecciona una fecha")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .setCalendarConstraints(new CalendarConstraints.Builder()
                                .setValidator(DateValidatorPointBackward.now())
                                .build())
                        .build();
                materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    fecha=dateFormat.format(new Date(selection));
                    datepicker.setText(fecha);
                });
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "tag");
            }
        });
        salir.setOnClickListener(v -> {
            closeHistorialFragment();
        });
        return view;
    }

    private void closeHistorialFragment(){
        Bundle bundle=new Bundle();
        bundle.putString("nombreEjercicio",tituloNombreEjercicio.getText().toString());
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_historialEjercicioFragment_to_detalleEjercicio,bundle);
    }

    public void agregarHistorial(int peso, int repeticiones, int series, String fecha) {
        Historial historial = new Historial(peso, repeticiones, series, fecha);
        ((AdapterHistorialEjercicio) resultado.getAdapter()).addHistorial(historial);
    }

}

