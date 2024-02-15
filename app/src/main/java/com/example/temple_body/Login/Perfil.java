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

import com.example.temple_body.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link informacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Perfil extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment informacion.
     */
    // TODO: Rename and change types and number of parameters
    public static informacion newInstance(String param1, String param2) {
        informacion fragment = new informacion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    EditText etNombre, etCorreo;
    Button btInformacion, btConfiguracion, btCerrarSesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_perfil, container, false);

        etNombre = layout.findViewById(R.id.APetNombrePerfil);
        etCorreo = layout.findViewById(R.id.APetEmail);
        btConfiguracion = layout.findViewById(R.id.APbtConfiguracion);
        btInformacion = layout.findViewById(R.id.APbtInformacion);
        btCerrarSesion = layout.findViewById(R.id.APbtCerrarSesion);

        etNombre.setText(loginPrincipal.User.toString());
        etCorreo.setText(loginPrincipal.User.toString().trim()+"@gmail.com");

        btConfiguracion.setOnClickListener((v)->{
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentLogin, new configuracion());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btInformacion.setOnClickListener((v)->{
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentLogin, new informacion());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btCerrarSesion.setOnClickListener((v)->{
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentLogin, new loginPrincipal());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        return layout;
    }
}
