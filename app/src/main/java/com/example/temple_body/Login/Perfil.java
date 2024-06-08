package com.example.temple_body.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.temple_body.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Perfil extends Fragment {
    TextView etNombre, etCorreo;
    Button btInformacion, btConfiguracion, btCerrarSesion, btHistorial;
    DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_perfil, container, false);

        etNombre = layout.findViewById(R.id.APtvNombrePerfil);
        etCorreo = layout.findViewById(R.id.APtvEmail);
        btConfiguracion = layout.findViewById(R.id.APbtConfiguracion);
        btHistorial = layout.findViewById(R.id.APbtHistorial);
        btInformacion = layout.findViewById(R.id.APbtInformacion);
        btCerrarSesion = layout.findViewById(R.id.APbtCerrarSesion);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        guardarLayoutLogin();
        cargarDatosUsuario();

        btConfiguracion.setOnClickListener((v) -> {
            viajarConfiguracion();
        });

        btHistorial.setOnClickListener((v) -> {
            viajarHistorial();
        });

        btInformacion.setOnClickListener((v) -> {
            viajarInformacion();
        });

        btCerrarSesion.setOnClickListener((v) -> {
            viajarLogin();
        });

        return layout;
    }

    private void cargarDatosUsuario() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
        String nombreUsuario = sharedPreferences.getString("nombreUsuario", "");
        String correo = sharedPreferences.getString("correo", "");
        etNombre.append(" "+nombreUsuario);
        etCorreo.setText(correo);
    }

    private void viajarConfiguracion() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_perfil_to_configuracion);
    }

    private void viajarInformacion() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_perfil_to_informacion);
    }

    private void viajarLogin() {
        FirebaseAuth.getInstance().signOut();
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_perfil_to_loginPrincipal);
    }

    private void viajarHistorial() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_perfil_to_historialFragment2);
    }
    private void guardarLayoutLogin() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("cargaLayoutLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("cargaLayoutLogin", 2);
        editor.apply();
    }
}
