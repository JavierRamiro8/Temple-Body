package com.example.temple_body.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.temple_body.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Perfil extends Fragment {
    TextView etNombre, etCorreo;
    Button btInformacion, btConfiguracion, btCerrarSesion;
    ImageButton ibAvatar;
    DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_perfil, container, false);

        etNombre = layout.findViewById(R.id.APtvNombrePerfil);
        etCorreo = layout.findViewById(R.id.APtvEmail);
        btConfiguracion = layout.findViewById(R.id.APbtConfiguracion);
        btInformacion = layout.findViewById(R.id.APbtInformacion);
        btCerrarSesion = layout.findViewById(R.id.APbtCerrarSesion);
        ibAvatar = layout.findViewById(R.id.APibAvatarPerfil);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Bundle args = getArguments();
        String usuario ="";
        String email ="";
        if (args != null) {
            usuario=args.getString("nombreUsuario");
            email=args.getString("email");
        }
        etNombre.setText(usuario);
        etCorreo.setText(email);

        btConfiguracion.setOnClickListener((v) -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentLogin, new configuracion());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btInformacion.setOnClickListener((v) -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentLogin, new informacion());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btCerrarSesion.setOnClickListener((v) -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentLogin, new loginPrincipal());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return layout;
    }
}

