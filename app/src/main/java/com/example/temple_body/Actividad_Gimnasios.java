package com.example.temple_body;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Actividad_Gimnasios extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;
    private FragmentContainerView fragmentSuplementos,fragmentGimnasiosCercanos,fragmentLogin,fragmentPerfil,fragmentCambioContrasena,fragmentConfiguracion,fragmentInformacion,fragmentRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_gimnasios);
        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.peso));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.manzana));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.mapa));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.usuario));
        fragmentSuplementos=findViewById(R.id.fragmentSuplementos);
        fragmentGimnasiosCercanos=findViewById(R.id.fragmentGimnasiosCercanos);
        fragmentLogin=findViewById(R.id.fragmentLogin);
        fragmentConfiguracion=findViewById(R.id.fragmentConfiguracion);
        fragmentInformacion=findViewById(R.id.fragmentInformacion);
        fragmentCambioContrasena=findViewById(R.id.fragmentCambioContrasena);
        fragmentPerfil=findViewById(R.id.fragmentPerfil);
        fragmentRegistro=findViewById(R.id.fragmentRegistro);

        fragmentConfiguracion.setVisibility(View.INVISIBLE);
        fragmentInformacion.setVisibility(View.INVISIBLE);
        fragmentCambioContrasena.setVisibility(View.INVISIBLE);
        fragmentPerfil.setVisibility(View.INVISIBLE);
        fragmentRegistro.setVisibility(View.INVISIBLE);

        fragmentSuplementos.setVisibility(View.INVISIBLE);
        fragmentGimnasiosCercanos.setVisibility(View.INVISIBLE);
        bottomNavigation.show(4,false);

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                Intent intent;

                switch(model.getId()){
                    case 1:
                        fragmentSuplementos.setVisibility(View.INVISIBLE);
                        fragmentGimnasiosCercanos.setVisibility(View.INVISIBLE);
                        fragmentLogin.setVisibility(View.INVISIBLE);
                        fragmentConfiguracion.setVisibility(View.INVISIBLE);
                        fragmentInformacion.setVisibility(View.INVISIBLE);
                        fragmentCambioContrasena.setVisibility(View.INVISIBLE);
                        fragmentPerfil.setVisibility(View.INVISIBLE);
                        fragmentRegistro.setVisibility(View.INVISIBLE);

                        break;
                    case 2:
                        fragmentSuplementos.setVisibility(View.VISIBLE);
                        fragmentGimnasiosCercanos.setVisibility(View.INVISIBLE);
                        fragmentLogin.setVisibility(View.INVISIBLE);
                        fragmentConfiguracion.setVisibility(View.INVISIBLE);
                        fragmentInformacion.setVisibility(View.INVISIBLE);
                        fragmentCambioContrasena.setVisibility(View.INVISIBLE);
                        fragmentPerfil.setVisibility(View.INVISIBLE);
                        fragmentRegistro.setVisibility(View.INVISIBLE);

                        break;
                    case 3:
                        fragmentGimnasiosCercanos.setVisibility(View.VISIBLE);
                        fragmentSuplementos.setVisibility(View.INVISIBLE);
                        fragmentLogin.setVisibility(View.INVISIBLE);
                        fragmentConfiguracion.setVisibility(View.INVISIBLE);
                        fragmentInformacion.setVisibility(View.INVISIBLE);
                        fragmentCambioContrasena.setVisibility(View.INVISIBLE);
                        fragmentPerfil.setVisibility(View.INVISIBLE);
                        fragmentRegistro.setVisibility(View.INVISIBLE);

                        break;
                    case 4:
                        fragmentLogin.setVisibility(View.VISIBLE);
                        fragmentSuplementos.setVisibility(View.INVISIBLE);
                        fragmentGimnasiosCercanos.setVisibility(View.INVISIBLE);
                        fragmentConfiguracion.setVisibility(View.INVISIBLE);
                        fragmentInformacion.setVisibility(View.INVISIBLE);
                        fragmentCambioContrasena.setVisibility(View.INVISIBLE);
                        fragmentPerfil.setVisibility(View.INVISIBLE);
                        fragmentRegistro.setVisibility(View.INVISIBLE);

                        break;
                }
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch(model.getId()){
                    case 1:
                        break;
                }
                return null;
            }
        });
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch(model.getId()){
                    case 2:
                        break;
                }
                return null;
            }
        });
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch(model.getId()){
                    case 3:
                        break;
                }
                return null;
            }
        });bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch(model.getId()){
                    case 4:
                        break;
                }
                return null;
            }
        });

    }
}