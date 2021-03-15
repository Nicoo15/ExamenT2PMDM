package com.example.examenpmdmrecu;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiContenedorDeVistas> {
        private ArrayList<Ciudades> lista_ciudades;
        public MiAdaptador(ArrayList<Ciudades> lista_contactos) {
            this.lista_ciudades = lista_ciudades;
        }
        @NonNull
        @Override
        public MiAdaptador.MiContenedorDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_coordenadas, parent, false);
            MiContenedorDeVistas contenedor = new MiContenedorDeVistas(vista);
            return contenedor;
        }

        @Override
        public void onBindViewHolder(@NonNull MiContenedorDeVistas holder, int position) {
            Ciudades c = lista_ciudades.get(position);
            holder.lat.setText.Double.ParseDouble(c.getLat());
            holder.lang.setText(c.getLang());
            //holder.timestamp.setText(c.getTimestamp());
        }

        @Override
        public int getItemCount() {
            return lista_ciudades.size();
        }

        public static class MiContenedorDeVistas extends RecyclerView.ViewHolder {
            public TextView lat, lang;

            public MiContenedorDeVistas(View vista) {
                super(vista);
                this.lat = vista.findViewById(R.id.lat);
                this.lang = vista.findViewById(R.id.lang);
                // this.timestamp = vista.findViewById(R.id.timestamp);
            }
        }
    }

