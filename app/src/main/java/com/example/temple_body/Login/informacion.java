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

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.temple_body.Login.fragmentsInformacion.fragCondicionesUso;
import com.example.temple_body.Login.fragmentsInformacion.fragPoliticasInformacion;
import com.example.temple_body.Login.fragmentsInformacion.fragReglamento;
import com.example.temple_body.R;
public class informacion extends Fragment {

    Button btInformacion, btCondiciones, btReglamento, btRegresar, btTelegram;
    ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_informacion, container, false);

        btInformacion = layout.findViewById(R.id.AIbtInformacionLegal);
        btReglamento = layout.findViewById(R.id.AIbtReglamento);
        btCondiciones = layout.findViewById(R.id.AIbtCondicionesUso);
        btRegresar = layout.findViewById(R.id.AIbtRegresar);
        btTelegram = layout.findViewById(R.id.AIbtTelegram);
        guardarLayoutLogin();
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                requestTelegram();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Error")
                        .setMessage("Necesitamos que acepte los permisos para poder realizar la accion.")
                        .setPositiveButton("Aceptar", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
       btInformacion.setOnClickListener(v -> {
           viajarInformacion();
       });

        btTelegram.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(
                    getActivity(), Manifest.permission.SEND_SMS) ==
                    PackageManager.PERMISSION_GRANTED) {
                requestTelegram();
            } else {
                requestPermissionLauncher.launch(Manifest.permission.SEND_SMS);
            }
        });

        btReglamento.setOnClickListener(v -> {
            viajarReglamento();
        });

        btCondiciones.setOnClickListener(v -> {
            viajarCondiciones();
        });

        btRegresar.setOnClickListener(v -> {
            viajarPerfil();
        });



        return layout;
    }
    private void requestTelegram() {
        try {
            String toNumber = "0034 644516218";

            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + toNumber));
            sendIntent.setPackage("com.telegram");
            startActivity(sendIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void viajarInformacion(){
        NavController nav= NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_informacion_to_fragPoliticasInformacion);
    }
    private void viajarCondiciones() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_informacion_to_fragCondicionesUso);
    }
    private void viajarReglamento() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_informacion_to_fragReglamento);
    }
    private void viajarPerfil() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_informacion_to_perfil);
    }
    private void guardarLayoutLogin() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("cargaLayoutLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("cargaLayoutLogin", 4);
        editor.apply();
    }
}