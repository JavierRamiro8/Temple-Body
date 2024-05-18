package com.example.temple_body.Ejercicios.Detalles.RecycleDetalles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.temple_body.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterDetalleEjercicio extends RecyclerView.Adapter<AdapterDetalleEjercicio.descripcionEjercicio> {

    private List<String> descripciones = new ArrayList<>();

    public AdapterDetalleEjercicio(String nombreEjercicio) {
    }

    @NonNull
    @Override
    public descripcionEjercicio onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_descripcion, parent, false);
        return new descripcionEjercicio(view);
    }

    @Override
    public void onBindViewHolder(@NonNull descripcionEjercicio holder, int position) {
        String descripcion = descripciones.get(position);
        holder.bind(descripcion);
    }

    @Override
    public int getItemCount() {
        return descripciones.size();
    }

    public void addDescripcion(String descripcion) {
        descripciones.add(descripcion);
        notifyDataSetChanged();
    }

    public static class descripcionEjercicio extends RecyclerView.ViewHolder {
        TextView descripcionEjercicioSeleccionado;

        public descripcionEjercicio(View itemView) {
            super(itemView);
            descripcionEjercicioSeleccionado = itemView.findViewById(R.id.textoDescripcion);
        }

        public void bind(String descripcion) {
            descripcionEjercicioSeleccionado.setText(descripcion);
        }
    }
}

