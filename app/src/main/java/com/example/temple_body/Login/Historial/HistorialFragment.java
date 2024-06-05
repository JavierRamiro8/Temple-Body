package com.example.temple_body.Login.Historial;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistorialFragment extends Fragment {
    private Button salir, filtrarFecha;
    private String fechaFiltrada;
    private RecyclerView resultado;
    private DatabaseReference mDatabase;
    private AdapterHistorial adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial_general, container, false);
        resultado = view.findViewById(R.id.recycleHistorial);

        salir = view.findViewById(R.id.BTSalir);
        filtrarFecha = view.findViewById(R.id.filtrarFecha);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        resultado.setLayoutManager(layoutManager);

        adapter = new AdapterHistorial(new ArrayList<>());
        resultado.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String idUsuario = user.getUid();

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
                                listaHistorial.add(new Historial(nombreEjercicio, Integer.parseInt(peso), Integer.parseInt(repeticiones), Integer.parseInt(series), fecha));
                            }
                            adapter.updateHistorialList(listaHistorial);
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

        salir.setOnClickListener(v -> closeHistorialFragment());
        return view;
    }

    private void closeHistorialFragment() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_historialFragment2_to_perfil);
    }
}

