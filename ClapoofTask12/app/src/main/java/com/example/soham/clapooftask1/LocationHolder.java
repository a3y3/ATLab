package com.example.soham.clapooftask1;

/**
 * Created by Soham on 02-May-17.
 */

public class LocationHolder {
    private int id;
    private String latitude, longitude;

    public LocationHolder(int id, String latitude, String longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public int getId() {
        return id;
    }
}
