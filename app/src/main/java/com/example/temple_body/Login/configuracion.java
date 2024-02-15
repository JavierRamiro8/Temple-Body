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
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.temple_body.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link configuracion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class configuracion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public configuracion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment configuracion.
     */
    // TODO: Rename and change types and number of parameters
    public static configuracion newInstance(String param1, String param2) {
        configuracion fragment = new configuracion();
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
    Button btinfProblema, btTelegram, btProbCuenta, btCambiaContra, btRegresar, btActualiza;
    ProgressBar pbActuliza;
    ActivityResultLauncher<String> requestPermissionLauncher;
    private static final double DELAY = 2000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_configuracion, container, false);

        btinfProblema = layout.findViewById(R.id.ACbtInformacionProblema);
        btCambiaContra = layout.findViewById(R.id.ACbtCambiarContra);
        btRegresar = layout.findViewById(R.id.ACbtRegresar);
        btTelegram = layout.findViewById(R.id.ACbtTelegram);
        btProbCuenta  = layout.findViewById(R.id.ACbtProblemasCuenta);
        btActualiza = layout.findViewById(R.id.ACbtActulizarAPP);
        pbActuliza = layout.findViewById(R.id.ACpbActualizacion);

        pbActuliza.setVisibility(View.INVISIBLE);

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                requestCorreo();
                requestLlamada();
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

        btProbCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        getActivity(), Manifest.permission.CALL_PHONE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    // You can use the API that requires the permission.
                    requestLlamada();
                } else {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissionLauncher.launch(Manifest.permission.CALL_PHONE);
                }

            }
        });
        btinfProblema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        getActivity(), Manifest.permission.SEND_SMS) ==
                        PackageManager.PERMISSION_GRANTED) {
                    requestCorreo();
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.SEND_SMS);
                }
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

        btCambiaContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentLogin, new CambioContrasena());
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

        btCambiaContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentLogin, new CambioContrasena());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btActualiza.setOnClickListener((v)->{
            pbActuliza.setVisibility(View.VISIBLE);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Info")
                    .setMessage("Tu APP esta actualizada a la ultima version!")
                    .setPositiveButton("Aceptar", null);

            AlertDialog dialog = builder.create();
            dialog.show();
            pbActuliza.setVisibility(View.INVISIBLE);

        });

        return layout;
    }
    private void requestCorreo(){
        Uri uri = Uri.parse("mailto:soporte.cuenta.templebody@gmail.com"); // Para abrir Gmail
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        i.putExtra("mail_body", "Problema con la cuenta.");
        startActivity(i);
    }
    private void requestLlamada(){
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:0034 911 66 66 66"));
        startActivity(phoneIntent);
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