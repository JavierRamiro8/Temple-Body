package com.example.temple_body.Login;

import android.Manifest;
import android.content.Intent;
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

import com.example.temple_body.Login.fragmentsInformacion.fragCondicionesUso;
import com.example.temple_body.Login.fragmentsInformacion.fragPoliticasInformacion;
import com.example.temple_body.Login.fragmentsInformacion.fragReglamento;
import com.example.temple_body.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link informacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class informacion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public informacion() {
        // Required empty public constructor
    }

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
        btInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentLogin, new fragPoliticasInformacion());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        getActivity(), Manifest.permission.SEND_SMS) ==
                        PackageManager.PERMISSION_GRANTED) {
                    requestTelegram();
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.SEND_SMS);
                }
            }
        });

        btReglamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentLogin, new fragReglamento());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btCondiciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentLogin, new fragCondicionesUso());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentLogin, new Perfil());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        return layout;
    }
    private void requestTelegram(){
        try {
            String toNumber = "0034 644516218";

            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + toNumber));
            sendIntent.setPackage("com.telegram");
            startActivity(sendIntent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}