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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginPrincipal extends Fragment {

    public loginPrincipal() {}

    private static final String MENSAJEERROR="Usuario o contraseña incorrectos, Es prueba, prueba.";
    EditText etCorreoElectronico;
    EditText etPassword;
    Button btIniciarSesion;
    TextView tvRegistrarse;

    FirebaseAuth mauth;
    DatabaseReference dr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_login_principal, container, false);

        etCorreoElectronico = layout.findViewById(R.id.ALetCorreoElectronico);
        etPassword = layout.findViewById(R.id.ALetPassword);
        btIniciarSesion = layout.findViewById(R.id.ALbtAccept);
        tvRegistrarse = layout.findViewById(R.id.ALtvRegisterlink);
        mauth=FirebaseAuth.getInstance();

        btIniciarSesion.setOnClickListener(v -> {
            String email= etCorreoElectronico.getText().toString();
            String contrasena=etPassword.getText().toString();
            if(email.isEmpty()){
                etCorreoElectronico.setError("Email vacio o nula.");
            }
            else if(contrasena.isEmpty()){
                etPassword.setError("Contraseña vacia o nula");
            }else{
                mauth.signInWithEmailAndPassword(email,contrasena).addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            dr= FirebaseDatabase.getInstance().getReference();
                            String idUsuario=FirebaseAuth.getInstance().getCurrentUser().getUid();
                            String correo=FirebaseAuth.getInstance().getCurrentUser().getEmail();
                            dr.child("Users").child(idUsuario).child("nombreUsuario").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        String usuario = task.getResult().getValue(String.class);
                                        viajarPerfil(correo, usuario);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                                        builder.setTitle("Error")
                                                .setMessage("Error al obtener el nombre de usuario")
                                                .setPositiveButton("Aceptar", null);

                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                            });
                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                            builder.setTitle("Error")
                                    .setMessage("Usuario o contraseña incorrectos")
                                    .setPositiveButton("Aceptar", null);

                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
            }

        });

        tvRegistrarse.setOnClickListener(v -> {
            viajarRegistro();
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

    private void viajarRegistro(){
        NavController nav= NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_loginPrincipal_to_registro);
    }
}