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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private RecyclerView resultado;
    DatabaseReference mDatabase;
    private int contadorDatos = 0;

    private AdapterHistorialEjercicio adapter;
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

        adapter = new AdapterHistorialEjercicio(new ArrayList<>());
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
            } else if (datepicker.getText().equals("SELECT DATE") || datepicker.getText().toString().isEmpty() || fecha == null) {
                datepicker.setError("Por favor Introduzca la fecha");
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Datos Registrados")
                        .setMessage("Tu historial se ha subido correctamente.")
                        .setPositiveButton("Aceptar", null);
                AlertDialog dialog = builder.create();
                dialog.show();
                registrarEjercicio(idUsuario);
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
        filtrarFecha.setOnClickListener(v -> {
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
                String[] splitFecha = fechaFiltrada.split("/");
                StringBuilder fechaFormateada = new StringBuilder();
                fechaFormateada.append(splitFecha[0]).append("-").append(splitFecha[1]).append("-").append(splitFecha[2]);
                filtrarFecha.setText("Fecha: " + fechaFiltrada);

                // Filtrar los datos en Firebase por la fecha seleccionada
                DatabaseReference ref = mDatabase.child("Ejercicios").child(idUsuario).child(fechaFormateada.toString());
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        List<Historial> listaHistorial = new ArrayList<>();
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot ejercicioSnapshot : dataSnapshot.getChildren()) {
                                String nombreEjercicio = ejercicioSnapshot.getKey();
                                String peso = ejercicioSnapshot.child("pesoEjercicio").getValue(String.class);
                                String repeticiones = ejercicioSnapshot.child("repeticionesEjercicio").getValue(String.class);
                                String series = ejercicioSnapshot.child("seriesEjercicio").getValue(String.class);
                                String fecha = ejercicioSnapshot.child("fechaEjercicio").getValue(String.class);
                                if(nombreEjercicio.equals(tituloNombreEjercicio.getText().toString())){
                                    agregarHistorial(nombreEjercicio, Integer.parseInt(peso), Integer.parseInt(repeticiones), Integer.parseInt(series), fecha, idUsuario);
                                    contadorDatos++;
                                }
                                if(contadorDatos == 0){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                                    builder.setTitle("No hay mas datos")
                                            .setMessage("No se encontraron mas datos para la fecha seleccionada.")
                                            .setPositiveButton("Aceptar", null);

                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                                }

                        } else {
                            Log.d("HistorialFragment", "No se encontraron datos para la fecha seleccionada.");
                            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                            builder.setTitle("No hay datos")
                                    .setMessage("No se encontraron datos para la fecha seleccionada.")
                                    .setPositiveButton("Aceptar", null);

                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("HistorialFragment", "Error en la consulta", databaseError.toException());
                    }
                });
            });
            materialDatePicker.show(getActivity().getSupportFragmentManager(), "tag");
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

    public void agregarHistorial(String nombreEjercicio, int peso, int repeticiones, int series, String fecha, String idUsuario) {
        Historial historial = new Historial(nombreEjercicio, peso, repeticiones, series, fecha);
        ((AdapterHistorialEjercicio) resultado.getAdapter()).addHistorial(historial);

    }
    private void registrarEjercicio(String idUsuario) {
        if (idUsuario != null) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("pesoEjercicio", peso.getText().toString());
            mapa.put("repeticionesEjercicio", repeticiones.getText().toString());
            mapa.put("seriesEjercicio", series.getText().toString());
            mapa.put("fechaEjercicio", fecha);
            String[] splitFecha = fecha.split("/");
            StringBuilder fechaBD = new StringBuilder("");
            fechaBD.append(splitFecha[0]).append("-").append(splitFecha[1]).append("-").append(splitFecha[2]);

            // Guardar los datos en la base de datos
            mDatabase.child("Ejercicios").child(idUsuario).child(fechaBD.toString()).child(tituloNombreEjercicio.getText().toString()).setValue(mapa);
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



