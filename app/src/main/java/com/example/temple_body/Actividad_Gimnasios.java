package com.example.temple_body;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.temple_body.Gimnasios.Gimnasios;
import com.example.temple_body.Gimnasios.ListaGimnasios;
import com.example.temple_body.Suplementacion.ListaSuplementos;
import com.example.temple_body.Suplementacion.Suplementos;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Actividad_Gimnasios extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_gimnasios);
        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.settings));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.settings));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.settings));
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                Intent intent;

                switch(model.getId()){
                    case 1:
                        intent=new Intent(Actividad_Gimnasios.this,ListaGimnasios.class);
                        startActivity(intent);

                        break;
                    case 2:
                        intent=new Intent(Actividad_Gimnasios.this, ListaSuplementos.class);
                        startActivity(intent);
                        break;
                    case 3:

                        break;
                    case 4:
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