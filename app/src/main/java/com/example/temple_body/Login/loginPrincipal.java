package com.example.temple_body.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.temple_body.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginPrincipal extends Fragment {

    public loginPrincipal() {
    }
    EditText etCorreoElectronico;
    EditText etPassword;
    Button btIniciarSesion;
    TextView tvRegistrarse, tvCambioContrasena;

    private FirebaseAuth mauth;
    private DatabaseReference dr;

    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_login_principal, container, false);
        guardarLayoutLogin();
        etCorreoElectronico = layout.findViewById(R.id.ALetCorreoElectronico);
        etPassword = layout.findViewById(R.id.ALetPassword);
        btIniciarSesion = layout.findViewById(R.id.ALbtAccept);
        tvRegistrarse = layout.findViewById(R.id.ALtvRegisterlink);
        tvCambioContrasena = layout.findViewById(R.id.tvCambioContrasena);
        mauth = FirebaseAuth.getInstance();

        btIniciarSesion.setOnClickListener(v -> {
            btIniciarSesion.setEnabled(false);
            String email = etCorreoElectronico.getText().toString();
            String contrasena = etPassword.getText().toString();
            if (email.isEmpty()) {
                etCorreoElectronico.setError("Email vacio o nula.");
                btIniciarSesion.setEnabled(true);
            } else if (contrasena.isEmpty()) {
                etPassword.setError("Contraseña vacia o nula");
                btIniciarSesion.setEnabled(true);
            } else {
                mauth.signInWithEmailAndPassword(email, contrasena).addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            assert user != null;
                            if (user.isEmailVerified()) {
                                String idUsuario = user.getUid();
                                String correo = user.getEmail();
                                dr = FirebaseDatabase.getInstance().getReference();
                                dr.child("Users").child(idUsuario).child("nombreUsuario").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            String usuario = task.getResult().getValue(String.class);
                                            viajarPerfil(correo, usuario);
                                        } else {
                                            new MaterialAlertDialogBuilder(requireContext())
                                                    .setTitle("Error")
                                                    .setMessage("Error al obtener el correo electronico del usuario")
                                                    .setPositiveButton("ACEPTAR", (dialog, which) -> {
                                                    })
                                                    .show();
                                            btIniciarSesion.setEnabled(true);
                                        }
                                    }
                                });
                            } else {
                                new MaterialAlertDialogBuilder(requireContext())
                                        .setTitle("Error")
                                        .setMessage("Recuerda que tienes que verificarte por correo electrónico, si no se ha enviado el correo de verificacion, contáctenos")
                                        .setPositiveButton("ACEPTAR", (dialog, which) -> {
                                        })
                                        .show();
                                btIniciarSesion.setEnabled(true);

                            }
                        } else {
                            new MaterialAlertDialogBuilder(requireContext())
                                    .setTitle("Error")
                                    .setMessage("Usuario o contraseña incorrectos")
                                    .setPositiveButton("ACEPTAR", (dialog, which) -> {
                                    })
                                    .show();
                            btIniciarSesion.setEnabled(true);

                        }
                    }
                });
            }
        });


        tvRegistrarse.setOnClickListener(v -> {
            viajarRegistro();
        });

        tvCambioContrasena.setOnClickListener(v -> {
            guardarLayoutIdaVuelta();
            viajarCambioContrasena();
        });

        return layout;
    }

    private void viajarPerfil(String correo, String usuario) {
        guardarCredencialesUsuario(usuario, correo);
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_loginPrincipal_to_perfil);
    }


    // Método para limpiar los datos del usuario de las preferencias compartidas
    private void guardarCredencialesUsuario(String nombreUsuario, String correo) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombreUsuario", nombreUsuario);
        editor.putString("correo", correo);
        editor.apply();
    }

    private void guardarLayoutIdaVuelta() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("cargaLayoutCambioContrasena", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("layoutLoginCambio", 1);
        editor.apply();
    }

    private void guardarLayoutLogin() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("cargaLayoutLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("cargaLayoutLogin", 1);
        editor.apply();
    }

    private void viajarRegistro() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_loginPrincipal_to_registro);
    }

    private void viajarCambioContrasena() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_loginPrincipal_to_cambioContrasena);
    }
}