package com.example.temple_body.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.temple_body.Actividad_Gimnasios;
import com.example.temple_body.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CambioContrasena extends Fragment {
    public CambioContrasena() {}

    EditText etCorreo;
    Button btCambiar, btRegresar;

    private static int regresoLayout =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_cambio_contrasena, container, false);
        cargarLayoutIdaVuelta();
        etCorreo = layout.findViewById(R.id.ACPetMail);
        btCambiar = layout.findViewById(R.id.ACPbtCambiar);
        btRegresar = layout.findViewById(R.id.ACPbtRegresar);

        btCambiar.setOnClickListener(v -> {
            int invalidado=0;
            if(etCorreo.getText().toString().isEmpty()){
                etCorreo.setError("Introduce correo válido");
                invalidado++;
            }
            if(invalidado==0){
                cambioContrasena(etCorreo.getText().toString().trim());
            }
        });

        btRegresar.setOnClickListener(v ->{
            if(regresoLayout ==0){
                viajarConfiguracion();
            }else{
                viajarLogin();
            }
        });
        return layout;
    }



    private void cambioContrasena(String correo) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        usersRef.orderByChild("email").equalTo(correo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    enviarCorreoRestablecimiento(correo);
                } else {
                    mostrarErrorCorreoNoEncontrado();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    private void enviarCorreoRestablecimiento(String correo) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(correo).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                mostrarExitoCorreoEnviado();
            } else {
                mostrarErrorCorreoNoEnviado();
            }
        });
    }

    private void mostrarErrorCorreoNoEncontrado() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Error")
                .setMessage("No se ha encontrado este correo en nuestros sistemas, correo invalido o no registrado")
                .setPositiveButton("OK", (dialog, which) -> {
                })
                .show();
    }

    private void mostrarExitoCorreoEnviado() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Confirmación")
                .setMessage("El correo se ha enviado con exito, revise su bandeja de entrada o la carpeta de spam!")
                .setPositiveButton("OK", (dialog, which) -> {
                })
                .show();
    }
    private void mostrarErrorCorreoNoEnviado() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Error")
                .setMessage("Error al enviar el correo de recuperacion de contraseña, correo invalido o no registrado")
                .setPositiveButton("OK", (dialog, which) -> {
                })
                .show();
    }

    private void cargarLayoutIdaVuelta() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("cargaLayoutCambioContrasena", Context.MODE_PRIVATE);
        regresoLayout = sharedPreferences.getInt("layoutLoginCambio",1);
    }
    private void viajarConfiguracion() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_cambioContrasena_to_configuracion);
    }
    private void viajarLogin() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_cambioContrasena_to_loginPrincipal);
    }
}