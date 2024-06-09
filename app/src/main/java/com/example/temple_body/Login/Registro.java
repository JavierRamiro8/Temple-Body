package com.example.temple_body.Login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.temple_body.Actividad_Gimnasios;
import com.example.temple_body.R;
import com.example.temple_body.Settings.Textos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registro extends Fragment {
    public Registro() {
    }

    EditText usuario, email, contrasena;

    CheckBox checkTerminos;

    TextView iniciarSesionText;
    Button registro;
    FirebaseAuth mauth;

    private static final int MINCARACTERESPASSWORD = 6;

    DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_registro, container, false);
        usuario = layout.findViewById(R.id.reg_et_usuario);
        contrasena = layout.findViewById(R.id.reg_et_password);
        email = layout.findViewById(R.id.reg_et_email);
        checkTerminos = layout.findViewById(R.id.cb_terminosYcondiciones);
        registro = layout.findViewById(R.id.btRegistro);
        iniciarSesionText=layout.findViewById(R.id.IniciarSesion);
        iniciarSesionText.setOnClickListener(v -> {
            viajarLogin();
        });
        mauth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        checkTerminos.setOnClickListener(v -> {
            Textos textos=new Textos();
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Politicas de privacidad")
                    .setMessage(textos.getPOLITICAS())
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            checkTerminos.setChecked(true);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            checkTerminos.setChecked(false);
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        registro.setOnClickListener(v -> {
            if (usuario.getText().toString().isEmpty()) {
                usuario.setError("Introduce nombre de usuario");
            }else if (!validarEmail() || email.getText().toString().isEmpty()) {
                email.setError("El correo está vacío o no es válido");
            }else if (contrasena.getText().toString().isEmpty()) {
                contrasena.setError("Introduce contraseña");
            }else if (!validarEmail() || email.getText().toString().isEmpty()) {
                email.setError("El correo está vacío o no es válido");
            }else if (contrasena.getText().length() <= MINCARACTERESPASSWORD) {
                contrasena.setError("la contraseña tiene que tener al menos 6 caracteres");
            } else if (!checkTerminos.isChecked()) {
                checkTerminos.setError("Lee las condiciones y términos");
            } else {
                registrarUsuario();
            }
        });

        return layout;
    }

    private void registrarUsuario() {
        mauth.createUserWithEmailAndPassword(email.getText().toString(), contrasena.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = mauth.getCurrentUser().getUid(); // Obtener el ID de usuario generado por Firebase
                    Map<String, Object> mapa = new HashMap<>();
                    mapa.put("nombreUsuario", usuario.getText().toString());
                    mapa.put("email", email.getText().toString());
                    mapa.put("contraseña", contrasena.getText().toString());

                    // Guardar los datos en la base de datos con el ID de usuario
                    mDatabase.child("Users").child(userId).setValue(mapa);
                    ventanaEmergenteAvisoEmailVer();
                    FirebaseUser user=mauth.getCurrentUser();
                    user.sendEmailVerification();
                    viajarLogin();
                } else {
                    new MaterialAlertDialogBuilder(requireContext())
                            .setTitle("ERROR")
                            .setMessage("Ya se esta usando esta cuenta de correo, por favor, quiere iniciar sesion?")
                            .setPositiveButton("ACEPTAR", (dialog, which) -> {
                                viajarLogin();
                            })
                            .setNegativeButton("CANCELAR", (dialog, which) -> {
                                dialog.dismiss();
                            })
                            .show();
                }
            }

            private void ventanaEmergenteAvisoEmailVer() {
                new MaterialAlertDialogBuilder(requireContext())
                        .setTitle("¡Bienvenido!")
                        .setMessage("Gracias por registrarte, te hemos enviado un correo para probar que no eres un robot ;)")
                        .setPositiveButton("ACEPTAR", (dialog, which) -> {})
                        .show();
            }
        });
    }


    private void viajarLogin() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_registro_to_loginPrincipal);
    }

    private boolean validarEmail(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.getText().toString().matches(emailPattern);
    }

}
