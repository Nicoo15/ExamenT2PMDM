package com.example.examenpmdmrecu;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button guardar;
    Marker marcador;
    private Object LatLng;
    EditText lat;
    EditText lng;
    ArrayList<Ciudades> lista_ciudades = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        FirebaseDatabase database = FirebaseDatabase.getInstance("https://webscrappingciudades-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Ciudades");
        Button guardar = (Button) findViewById(R.id.guardar);
        EditText lat = (EditText) findViewById(R.id.coordlat);
        EditText lng = (EditText) findViewById(R.id.coordlong);
        Double lati = Double.parseDouble(lat.getText().toString());
        Double lang = Double.parseDouble(lng.getText().toString());
        Ciudades c = new Ciudades(lat, lng);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText latitud = (EditText) findViewById(R.id.coordlat);
                EditText longitud = (EditText) findViewById(R.id.coordlong);

                Double lati = Double.parseDouble(latitud.getText().toString());
                Double lang = Double.parseDouble(longitud.getText().toString());

                Ciudades c = new Ciudades(lati, lang);
                myRef.push().setValue(c);
             //   mostrarMarker();

            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> datos = snapshot.getChildren();
                for (DataSnapshot d : datos) {
                    String key = d.getKey();
                    Ciudades ciudad = d.getValue(Ciudades.class);
                    Log.d("DATOS", key + ":" + ciudad.toString());


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void mostrarMarker() {
        if (marcador != null) {
            marcador.remove();
        }
        {

            Ciudades c = new Ciudades(lat,lng);

            LatLng ciudadAct = new LatLng(c.getLat(), c.getLng());

            marcador = mMap.addMarker(new MarkerOptions().position(ciudadAct));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ciudadAct, 5.5f));
            GoogleMap.OnMarkerClickListener oyente_marcador = new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Intent intent = new Intent(com.example.examenpmdmrecu.MapsActivity.this, ListaCoordenadas.class);
                    startActivity(intent);
                    return true;
                }
            };


            /**
             * Manipulates the map once available.
             * This callback is triggered when the map is ready to be used.
             * This is where we can add markers or lines, add listeners or move the camera. In this case,
             * we just add a marker near Sydney, Australia.
             * If Google Play services is not installed on the device, the user will be prompted to install
             * it inside the SupportMapFragment. This method will only be triggered once the user has
             * installed Google Play services and returned to the app.
             */


            }
        }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }
}

