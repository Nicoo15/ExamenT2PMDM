package com.example.examenpmdmrecu;



import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaCoordenadas  extends AppCompatActivity {
    ArrayList<Ciudades> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_coordenadas);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Ciudades");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Iterable<DataSnapshot> data = snapshot.getChildren();
                for (DataSnapshot d : data) {
                    Ciudades c = d.getValue(Ciudades.class);
                    lista.add(c);
                    Log.d("bi", lista.toString());
                    mostrar();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("bi", error.getDetails());
            }
        });


    }

    private void mostrar() {
        RecyclerView rec = findViewById(R.id.mi_reycler);
        RecyclerView.LayoutManager gestor = new LinearLayoutManager(this);
        MiAdaptador adaptador = new MiAdaptador(lista);
        rec.setLayoutManager(gestor);
        rec.setAdapter(adaptador);
    }
}

