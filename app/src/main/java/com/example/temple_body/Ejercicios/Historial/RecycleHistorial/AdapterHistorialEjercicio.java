package com.example.temple_body.Ejercicios.Historial.RecycleHistorial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterHistorialEjercicio extends RecyclerView.Adapter<AdapterHistorialEjercicio.HistorialEjercicioViewHolder> {

    private List<Historial> historiales;

    public AdapterHistorialEjercicio(List<Historial> historiales) {
        this.historiales = historiales;
    }

    @NonNull
    @Override
    public HistorialEjercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_historial, parent, false);
        return new HistorialEjercicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialEjercicioViewHolder holder, int position) {
        Historial historial = historiales.get(position);
        holder.bind(historial);
    }

    @Override
    public int getItemCount() {
        return historiales.size();
    }

    public void addHistorial(Historial historial) {
        historiales.add(historial);
        notifyDataSetChanged();
    }

    public static class HistorialEjercicioViewHolder extends RecyclerView.ViewHolder {

        TextView fecha, peso, repeticiones, series, nombre;

        public HistorialEjercicioViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.fechaEjercicio);
            nombre = itemView.findViewById(R.id.nombreEjercicio);
            peso = itemView.findViewById(R.id.pesoEjercicio);
            repeticiones = itemView.findViewById(R.id.repeticionesEjercicio);
            series = itemView.findViewById(R.id.seriesEjercicio);
        }

        public void bind(Historial historial) {
            fecha.setText(historial.getFecha());
            nombre.setText("Nombre del ejercicio: " +historial.getNombreEjercicio());
            peso.setText("Peso: " + String.valueOf(historial.getPeso() + "kg"));
            repeticiones.setText("Repeticiones: " + String.valueOf(historial.getRepeticiones()));
            series.setText("Series: " + String.valueOf(historial.getSeries()));
        }
    }
}

