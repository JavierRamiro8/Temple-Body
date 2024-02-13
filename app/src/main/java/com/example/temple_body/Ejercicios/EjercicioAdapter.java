package com.example.temple_body.Ejercicios;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;

import java.util.List;

public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.EjercicioViewHolder> {
    private List<Ejercicio> listaEjercicios;

    public EjercicioAdapter(List<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    @Override
    public EjercicioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ejercicio, parent, false);
        return new EjercicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder( EjercicioViewHolder holder, int position) {
        Ejercicio ejercicio = listaEjercicios.get(position);
        holder.bind(ejercicio);
    }

    @Override
    public int getItemCount() {
        return listaEjercicios.size();
    }

    public static class EjercicioViewHolder extends RecyclerView.ViewHolder {
        TextView nombreEjercicio, musculoEjercicio, dificultadEjercicio;

        public EjercicioViewHolder(View itemView) {
            super(itemView);
            nombreEjercicio = itemView.findViewById(R.id.nombreEjercicio);
            musculoEjercicio = itemView.findViewById(R.id.musculoEjercicio);
            dificultadEjercicio = itemView.findViewById(R.id.dificultadEjercicio);
        }

        public void bind(Ejercicio ejercicio) {
            nombreEjercicio.setText(ejercicio.getName());
            musculoEjercicio.setText(ejercicio.getMuscle().toUpperCase());
            dificultadEjercicio.setText(ejercicio.getDifficulty().toUpperCase());
        }
    }
}

