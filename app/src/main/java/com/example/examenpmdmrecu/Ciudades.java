package com.example.examenpmdmrecu;


import android.widget.EditText;

public class Ciudades {
    private Double lat, lng;

    public Ciudades(EditText lat, EditText lng) {
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }



    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }



    @Override
    public String toString() {
        return "Ciudad{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public Ciudades(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;

    }
}