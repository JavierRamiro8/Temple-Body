package com.example.temple_body.Suplementacion;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;
public class SuplementosFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String OPCION_SELECTED;
    public boolean OPCION_SELECCIONADA;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView rcv;
    SuplementosAdapter a;
    public String nombre = "";
    ActivityResultLauncher<String> requestPermissionLauncher;
    Spinner spinnerSuplementos;
    TextView proe, hsn, prozis, big_supps, marcasRecomendadas;
    public static SuplementosFragment newInstance(String param1, String param2) {
        SuplementosFragment fragment = new SuplementosFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_suplementos, container, false);
        spinnerSuplementos = layout.findViewById(R.id.spinnerEjercicios);

        rcv = layout.findViewById(R.id.listaContenedorSuplementos);
        rcv.setLayoutManager(new LinearLayoutManager(requireContext()));

        marcasRecomendadas = layout.findViewById(R.id.tvMarcasRecomendadas);
        proe = layout.findViewById(R.id.tvProe);
        hsn = layout.findViewById(R.id.tvHsn);
        prozis = layout.findViewById(R.id.tvProzis);
        big_supps = layout.findViewById(R.id.tvBig);

        marcasRecomendadas.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        proe.setText("Proe-Nutrition");
        proe.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        hsn.setText("HSN");
        hsn.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        prozis.setText("Prozis");
        prozis.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        big_supps.setText("Big-Supps");
        big_supps.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        spinnerSuplementos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    OPCION_SELECTED = item.toString();
                    OPCION_SELECCIONADA = !OPCION_SELECTED.equals("Select suplement");
                    a = new SuplementosAdapter(Suplementos.generador(OPCION_SELECTED));
                    rcv.setAdapter(a);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });

        proe.setOnClickListener((View v) -> {
            abrirWebConPermiso("https://proenutrition.es/", "https://proenutrition.es/module/iqitsearch/searchiqit?s=");
        });
        hsn.setOnClickListener((View v) -> {
            abrirWebConPermiso("https://www.hsnstore.com/", "https://www.hsnstore.com/?q=");
        });
        prozis.setOnClickListener((View v) -> {
            abrirWebConPermiso("https://www.prozis.com/es/es", "https://www.prozis.com/es/es/search?text=");
        });
        big_supps.setOnClickListener((View v) -> {
            abrirWebConPermiso("https://bigsupps.site/", "https://bigsupps.site/search?type=product,article,page&q=");
        });

        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your app.
                abrirWeb("");
            } else {
                // Explain to the user that the feature is unavailable because the feature requires a permission that the user has denied.
                // At the same time, respect the user's decision. Don't link to system settings in an effort to convince the user to change their decision.
            }
        });

        return layout;
    }

    private void abrirWebConPermiso(String defaultUrl, String searchUrl) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            abrirWeb(OPCION_SELECCIONADA ? searchUrl + OPCION_SELECTED : defaultUrl);
        } else {
            requestPermissionLauncher.launch(Manifest.permission.INTERNET);
        }
    }

    private void abrirWeb(String webSolicitada) {
        Uri uri = Uri.parse(webSolicitada);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(webIntent);
    }
}
