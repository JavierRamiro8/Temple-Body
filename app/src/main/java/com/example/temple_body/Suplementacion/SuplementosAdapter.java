package com.example.temple_body.Suplementacion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.temple_body.R;

import java.util.ArrayList;

public class SuplementosAdapter extends RecyclerView.Adapter<SuplementosAdapter.ViewHolder> {
    private ArrayList<Suplementos> datos;

    /*
     * Relacionado con el evento.
     */
    public interface ItemClickListener {
        void onClick(View view, int position, Suplementos gimnasio);
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView nombre,descripcion;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            nombre = (TextView) view.findViewById(R.id.nombreGimnasio);
            descripcion = (TextView) view.findViewById(R.id.descripcionSuplemento);
            view.setOnClickListener(this);
        }

        public void setInfo(String i_nombre, String i_descripcion) {
            nombre.setText(i_nombre);
            descripcion.setText(i_descripcion);

        }

        @Override
        public void onClick(View view) {
            // Si tengo un manejador de evento lo propago con el índice
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition(), datos.get(getAdapterPosition()));
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public SuplementosAdapter(ArrayList<Suplementos> dataSet) {
        datos = new ArrayList<Suplementos>();
        add(dataSet);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_suplemento, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Suplementos e = datos.get(position);
        viewHolder.setInfo(e.getNombre(),e.getDescripcion());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void add(ArrayList<Suplementos> dataSet){
        datos.addAll(dataSet);
        notifyDataSetChanged();
    }
}
