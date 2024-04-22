package com.example.temple_body.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.temple_body.R;
import com.google.firebase.auth.FirebaseAuth;

public class loginPrincipal extends Fragment {

    public loginPrincipal() {}
    public static  final  String User = "prueba";
    public static  final  String Pass = "prueba";
    EditText etUsuario;
    EditText etPassword;
    Button btIniciarSesion;
    TextView tvRegistrarse;

    FirebaseAuth mauth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_login_principal, container, false);

        etUsuario = layout.findViewById(R.id.ALetUsuario);
        etPassword = layout.findViewById(R.id.ALetPassword);
        btIniciarSesion = layout.findViewById(R.id.ALbtAccept);
        tvRegistrarse = layout.findViewById(R.id.ALtvRegisterlink);
        mauth=FirebaseAuth.getInstance();

        btIniciarSesion.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (usuario.equals(User) && password.equals(Pass)) {
                // Las credenciales son correctas, navegar al perfil Fragment
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentLogin, new Perfil());
                transaction.addToBackStack(null);
                transaction.commit();
            } else {
                // Las credenciales son incorrectas, mostrar AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Error")
                        .setMessage("Usuario o contraseña incorrectos, Es prueba, prueba.")
                        .setPositiveButton("Aceptar", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        tvRegistrarse.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentLogin, new Registro());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return layout;
    }
}