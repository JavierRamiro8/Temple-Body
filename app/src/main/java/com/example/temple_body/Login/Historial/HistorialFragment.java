package com.example.temple_body.Login.Historial;

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

import com.example.temple_body.Login.Historial.AdapterHistorial;
import com.example.temple_body.Login.Historial.Historial;
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

public class HistorialFragment extends Fragment {
    private Button salir, filtrarFecha;
    private String fechaFiltrada;
    private MutableLiveData<List<Historial>> liveHistorial = new MutableLiveData<>();
    private RecyclerView resultado;
    DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial_general, container, false);
        resultado = view.findViewById(R.id.recycleHistorial);

        salir = view.findViewById(R.id.BTSalir);

        filtrarFecha = view.findViewById(R.id.filtrarFecha);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        resultado.setLayoutManager(layoutManager);
        AdapterHistorial adapter = new AdapterHistorial(new ArrayList<>());
        resultado.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String idUsuario = user.getUid();
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
                    String fechaString = fechaFiltrada.toString();
                    String[] splitFecha = fechaString.split("/");
                    StringBuilder fechaFormateada = new StringBuilder("");
                    fechaFormateada.append(splitFecha[0]).append("-").append(splitFecha[1]).append("-").append(splitFecha[2]);
                    filtrarFecha.setText("Fecha: " + fechaFiltrada + "\n\n Filtrar por fecha");
                    Log.e("Error: ", idUsuario);

                    Log.e("Error: ", String.valueOf(fechaFormateada));

                    //Aqui tiene que ir la lógica de filtrar la fecha y la bbdd
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Ejercicios").child(idUsuario).child(String.valueOf(fechaFormateada));

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot ejercicioSnapshot : dataSnapshot.getChildren()) {
                                    String nombreEjercicio = ejercicioSnapshot.getKey();
                                    String peso = ejercicioSnapshot.child("pesoEjercicio").getValue(String.class);
                                    String repeticiones = ejercicioSnapshot.child("repeticionesEjercicio").getValue(String.class);
                                    String series = ejercicioSnapshot.child("seriesEjercicio").getValue(String.class);
                                    String fecha = ejercicioSnapshot.child("fechaEjercicio").getValue(String.class);
                                    agregarHistorial(nombreEjercicio,Integer.parseInt(peso), Integer.parseInt(repeticiones), Integer.parseInt(series), fecha, idUsuario);
                                }

                                    // Llama al método para agregar datos y actualizar el TextView

                            } else {
                                Log.d("MainActivity", "No se encontraron datos para la fecha y el ejercicio seleccionados.");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w("MainActivity", "Error en la consulta", databaseError.toException());
                        }
                    });


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
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_historialFragment_to_perfil);
    }

    public void agregarHistorial(String nombreEjercicio,int peso, int repeticiones, int series, String fecha, String idUsuario) {
        Historial historial = new Historial(nombreEjercicio,peso, repeticiones, series, fecha);
        ((AdapterHistorial) resultado.getAdapter()).addHistorial(historial);
    }


}
