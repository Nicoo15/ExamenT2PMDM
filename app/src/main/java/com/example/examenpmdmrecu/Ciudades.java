package com.example.examenpmdmrecu;


import android.widget.EditText;

public class Ciudades {
    private Double lat, lng;
    String fecha;

    public Ciudades() {
    }

    public Ciudades(Double lat, Double lng, String fecha) {
        this.lat = lat;
        this.lng = lng;
        this.fecha = fecha;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getFecha() {
        return fecha;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Ciudades{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}

