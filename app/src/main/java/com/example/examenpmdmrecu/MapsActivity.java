package com.example.examenpmdmrecu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button guardar;
    private PolylineOptions ruta = new PolylineOptions();
    private boolean mapaCentrado = false;
    private LocationListener oyente;
    private LocationManager lm;
    private DatabaseReference myRef;
    private Timestamp time;
    Marker marcador;
    Boolean activar= false;


    @RequiresApi(api = Build.VERSION_CODES.M)
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


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activar = true;
                Actualizacion();


                //   mostrarMarker();

            }

        });
    }

/*
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
             * installed Google Play services and returned to the app.*/



         @Override
    public void onMapReady(GoogleMap googleMap) {
             mMap= googleMap;

    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //pedirActualizaciones();
                guardar.setEnabled(true);
            }
        }else{
            guardar.setEnabled(false);
        }
    }





    public void Actualizacion() {
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        oyente = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                while (activar == true) {
                   anadir(location);
                }
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 0, oyente);
    }

    private void anadir(Location location) {
        mMap.clear();
        LatLng marcado = new LatLng(location.getLatitude(), location.getLongitude());
        DateFormat format = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        Date fecha = new Date(location.getTime());
        String fech = format.format(fecha);

        marcador = mMap.addMarker(new MarkerOptions().position(marcado));
        marcador.setTag("Localizacion actual");
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marcado, 5.5f));
        GoogleMap.OnMarkerClickListener oyente_marcador = new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(com.example.examenpmdmrecu.MapsActivity.this, ListaCoordenadas.class);
                startActivity(intent);
                return true;
            }
        };

    }


}

