package com.example.temple_body.Login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.temple_body.R;

public class configuracion extends Fragment {

    public configuracion() {}
    Button btinfProblema,btRegresar, btModificar;
    ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_configuracion, container, false);

        btinfProblema = layout.findViewById(R.id.ACbtInformacionProblema);
        btRegresar = layout.findViewById(R.id.ACbtRegresar);
        btModificar = layout.findViewById(R.id.ACbtModificarDatos);
        guardarLayoutLogin();

        btinfProblema.setOnClickListener(v -> {
                requestCorreo();
        });

        btRegresar.setOnClickListener(v -> {
            viajarPerfil();
        });

        btModificar.setOnClickListener(v -> {
            guardarLayoutIdaVuelta();
            viajarCambioCotrasena();
        });

        return layout;
    }
    private void requestCorreo(){
        Uri uri = Uri.parse("mailto:soporte@templebody.es");
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        i.putExtra("mail_body", "Problema con la cuenta.");
        startActivity(i);
    }
    private void guardarLayoutIdaVuelta() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("cargaLayoutCambioContrasena", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("layoutLoginCambio", 0);
        editor.apply();
    }
    private void viajarPerfil() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_configuracion_to_perfil);
    }
    private void viajarCambioCotrasena() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_configuracion_to_cambioContrasena);
    }
    private void guardarLayoutLogin() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("cargaLayoutLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("cargaLayoutLogin", 3);
        editor.apply();
    }

}