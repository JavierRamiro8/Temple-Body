package com.example.temple_body.Ejercicios.Historial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private TextView resultado;


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
        resultado = view.findViewById(R.id.TVResultado);
        generar = view.findViewById(R.id.BTGenerar);
        salir = view.findViewById(R.id.BTSalir);
        datepicker=view.findViewById(R.id.datePicker);
        Bundle args=getArguments();
        if (args != null && args.containsKey(nombreEjercicio)) {
            String getTituloEjercicio = args.getString(nombreEjercicio);
            tituloNombreEjercicio.setText(getTituloEjercicio);
        }
        generar.setOnClickListener(v -> {
            if (peso.getText().equals(" ") | repeticiones.getText().equals(" ") | series.getText().equals(" ")) {
                peso.setText(" ");
                repeticiones.setText(" ");
                series.setText(" ");
            } else {
                agregarHistorial(Integer.valueOf(peso.getText().toString()), Integer.valueOf(repeticiones.getText().toString()), Integer.valueOf(series.getText().toString()),fecha);
            }
        });
        liveHistorial.observe(getViewLifecycleOwner(), new Observer<List<Historial>>() {
            @Override
            public void onChanged(List<Historial> historial) {
                StringBuilder historialText = new StringBuilder();
                for (Historial ejercicio : historial) {
                    historialText.append("Peso: ").append(ejercicio.getPeso()).append(" Repeticiones: ").append(ejercicio.getRepeticiones()).append(" Series: ").append(ejercicio.getSeries()).append(" fecha: "+ ejercicio.getFecha()).append("\n");
                }
                resultado.setText(historialText.toString());
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
        List<Historial> listaHistorial = liveHistorial.getValue();
        if (listaHistorial == null) {
            listaHistorial = new ArrayList<>();
        }
        listaHistorial.add(new Historial(peso, repeticiones, series,fecha));
        liveHistorial.setValue(listaHistorial);
    }
}

