package com.example.u2tema1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiNuevoAdaptador extends RecyclerView.Adapter<MiNuevoAdaptador.ViewHolder> {
    private LayoutInflater inflador;
    private ArrayList<Cliente> lista;
    Context micontext;

    public MainActivity activity;

    public MiNuevoAdaptador(Context context, ArrayList<Cliente> lista) {
        this.lista = lista;
        inflador = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.minuevoitem, parent, false);
        micontext = parent.getContext();
        return new ViewHolder(v);

    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        holder.titulo.setText(lista.get(i).getNombre()+" "+lista.get(i).getApellido());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(micontext.getApplicationContext(), Datos.class);
                intent.putExtra("codigo",lista.get(i).getcodigo());
                intent.putExtra("nombre",lista.get(i).getNombre());
                intent.putExtra("ape",lista.get(i).getApellido());
               intent.putExtra("sexo",lista.get(i).getSexo());
               intent.putExtra("celular",lista.get(i).getCelular());
               intent.putExtra("Domicilio",lista.get(i).getDomicilio());
                micontext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo, subtitutlo;
        public ImageView icon;
        ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView)itemView.findViewById(R.id.titulo);
            subtitutlo = (TextView)itemView.findViewById(R.id.subtitulo);
            icon = (ImageView)itemView.findViewById(R.id.icono);
        }
    }
    public void update(ArrayList<Cliente> datas){
        lista.clear();
        lista.addAll(datas);
        notifyDataSetChanged();
    }
}
