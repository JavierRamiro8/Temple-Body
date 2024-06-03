package com.example.temple_body.Ejercicios.Historial;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.temple_body.Ejercicios.Historial.RecycleHistorial.AdapterHistorialEjercicio;
import com.example.temple_body.Ejercicios.Historial.RecycleHistorial.Historial;
import com.example.temple_body.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HistorialEjercicioFragment extends Fragment {

    private static String nombreEjercicio = "nombreEjercicio";
    private EditText peso;
    private EditText repeticiones;
    private EditText series;
    private TextView tituloNombreEjercicio;
    private Button datepicker, generar, salir, filtrarFecha;
    private String fecha, fechaFiltrada;
    private MutableLiveData<List<Historial>> liveHistorial = new MutableLiveData<>();
    private RecyclerView resultado;
    FirebaseAuth mauth;
    DatabaseReference mDatabase;


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
        datepicker = view.findViewById(R.id.datePicker);
        filtrarFecha = view.findViewById(R.id.filtrarFecha);
        Bundle args = getArguments();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        resultado.setLayoutManager(layoutManager);
        AdapterHistorialEjercicio adapter = new AdapterHistorialEjercicio(new ArrayList<>());
        resultado.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String idUsuario = user.getUid();

        if (args != null && args.containsKey(nombreEjercicio)) {
            String getTituloEjercicio = args.getString(nombreEjercicio);
            tituloNombreEjercicio.setText(getTituloEjercicio);
        }
        generar.setOnClickListener(v -> {
            if (peso.getText().toString().isEmpty()) {
                peso.setError("Por favor Introduzca Peso");
            } else if (repeticiones.getText().toString().isEmpty()) {
                repeticiones.setError("Por favor Introduzca Repeticiones");
            } else if (series.getText().toString().isEmpty()) {
                series.setError("Por favor Introduzca las series");
            } else if (datepicker.getText().equals("Selecciona Fecha")) {
                datepicker.setError("Por favor Introduzca la fecha");
            } else {
                agregarHistorial(Integer.valueOf(peso.getText().toString()), Integer.valueOf(repeticiones.getText().toString()), Integer.valueOf(series.getText().toString()), fecha, idUsuario);
            }
        });
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Selecciona una fecha")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .setCalendarConstraints(new CalendarConstraints.Builder()
                                .setValidator(DateValidatorPointBackward.now())
                                .build())
                        .build();
                materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    fecha = dateFormat.format(new Date(selection));
                    datepicker.setText(fecha);
                });
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "tag");
            }
        });
        filtrarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Selecciona una fecha")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .setCalendarConstraints(new CalendarConstraints.Builder()
                                .setValidator(DateValidatorPointBackward.now())
                                .build())
                        .build();
                materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    fechaFiltrada = dateFormat.format(new Date(selection));
                    filtrarFecha.setText("Fecha: " + fechaFiltrada + "\n\n Filtrar por fecha");
                    Log.e("Error: ", idUsuario);
                    //Aqui tiene que ir la lógica de filtrar la fecha y la bbdd
                    SharedPreferences sharedPreferences = requireContext().getSharedPreferences("datos_ejercicio", Context.MODE_PRIVATE);
                    String nombreEjercicio = sharedPreferences.getString("nombreEjercicio", "");
                    String fechaEjercicio = sharedPreferences.getString("fechaEjercicio", "");
                    String pesoEjercicio = sharedPreferences.getString("pesoEjercicio", "");
                    String repeticionesEjercicio = sharedPreferences.getString("repeticionesEjercicio", "");
                    String seriesEjercicio = sharedPreferences.getString("seriesEjercicio", "");
                    if(fechaFiltrada.toString() == fecha.toString() && nombreEjercicio == tituloNombreEjercicio.getText().toString()){
                        agregarHistorial(Integer.parseInt(pesoEjercicio),Integer.parseInt(repeticionesEjercicio),Integer.parseInt(seriesEjercicio),fechaEjercicio, idUsuario);
                    }
                });
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "tag");
            }
        });
        salir.setOnClickListener(v -> {
            closeHistorialFragment();
        });
        return view;
    }

    private void closeHistorialFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("nombreEjercicio", tituloNombreEjercicio.getText().toString());
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_historialEjercicioFragment_to_detalleEjercicio, bundle);
    }

    public void agregarHistorial(int peso, int repeticiones, int series, String fecha, String idUsuario) {
        Historial historial = new Historial(peso, repeticiones, series, fecha);
        ((AdapterHistorialEjercicio) resultado.getAdapter()).addHistorial(historial);
        registrarEjercicio(idUsuario);
    }
    private void registrarEjercicio(String idUsuario) {
        if (idUsuario != null) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("pesoEjercicio", peso.getText().toString());
            mapa.put("repeticionesEjercicio", repeticiones.getText().toString());
            mapa.put("seriesEjercicio", series.getText().toString());
            mapa.put("fechaEjercicio", fecha.toString());
            String[] splitFecha = fecha.split("/");
            StringBuilder fechaBD = new StringBuilder("");
            fechaBD.append(splitFecha[0]).append("-").append(splitFecha[1]).append("-").append(splitFecha[2]);

            // Guardar los datos en la base de datos
            mDatabase.child("Ejercicios").child(idUsuario).child(tituloNombreEjercicio.getText().toString()).child(fechaBD.toString()).setValue(mapa);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Error")
                    .setMessage("Datos mal introducidos")
                    .setPositiveButton("Aceptar", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

}



