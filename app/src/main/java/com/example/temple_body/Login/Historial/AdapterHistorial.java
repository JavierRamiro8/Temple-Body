package com.example.temple_body.Login.Historial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;

import java.util.List;

public class AdapterHistorial extends RecyclerView.Adapter<AdapterHistorial.HistorialEjercicioViewHolder> {
    private List<Historial> historiales;

    public AdapterHistorial(List<Historial> historiales) {
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

    public void updateHistorialList(List<Historial> nuevosHistoriales) {
        historiales.clear();
        historiales.addAll(nuevosHistoriales);
        notifyDataSetChanged();
    }

    public static class HistorialEjercicioViewHolder extends RecyclerView.ViewHolder {
        TextView nombreEjercicio, fecha, peso, repeticiones, series;

        public HistorialEjercicioViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.fechaEjercicio);
            nombreEjercicio = itemView.findViewById(R.id.nombreEjercicio);
            peso = itemView.findViewById(R.id.pesoEjercicio);
            repeticiones = itemView.findViewById(R.id.repeticionesEjercicio);
            series = itemView.findViewById(R.id.seriesEjercicio);
        }

        public void bind(Historial historial) {
            fecha.setText(historial.getFecha());
            nombreEjercicio.setText("Ejercicio: " + historial.getNombreEjercicio());
            peso.setText("Peso: " + historial.getPeso() + "kg");
            repeticiones.setText("Repeticiones: " + historial.getRepeticiones());
            series.setText("Series: " + historial.getSeries());
        }
    }
}


