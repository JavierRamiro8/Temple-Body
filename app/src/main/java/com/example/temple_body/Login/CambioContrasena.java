package com.example.temple_body.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.temple_body.R;
public class CambioContrasena extends Fragment {
    public CambioContrasena() {}

    EditText etUsuario, etCorreo, etPassOld, etPassNew;
    Button btCambiar, btRegresar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_cambio_contrasena, container, false);

        etUsuario = layout.findViewById(R.id.ACPetUsuario);
        etCorreo = layout.findViewById(R.id.ACPetMail);
        etPassOld = layout.findViewById(R.id.ACPetOLDpassword);
        etPassNew = layout.findViewById(R.id.ACPetNEWpassword);
        btCambiar = layout.findViewById(R.id.ACPbtCambiar);
        btRegresar = layout.findViewById(R.id.ACPbtRegresar);

        btCambiar.setOnClickListener(v -> {
            viajarPerfil();
        });

        btRegresar.setOnClickListener(v ->{
            viajarConfiguracion();
        });
        return layout;
    }
    private void viajarPerfil() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_cambioContrasena_to_perfil2);
    }
    private void viajarConfiguracion() {
        NavController nav = NavHostFragment.findNavController(this);
        nav.navigate(R.id.action_cambioContrasena_to_configuracion);
    }
}