package com.example.raksa.hikinggeolocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    LocationManager locationManager;
    LocationListener locationListener;

    //View
    TextView textViewLatitude,textViewLongtitude,textViewAccuracy,textViewAltitude
            ,textViewVillage,textViewDistrict,textViewCountry,textViewCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        }

        //get View reference
        textViewLatitude = findViewById(R.id.textViewLatitude);
        textViewLongtitude = findViewById(R.id.textViewLongtitude);
        textViewAccuracy = findViewById(R.id.textViewAccuracy);
        textViewAltitude = findViewById(R.id.textViewAltitude);
        textViewVillage = findViewById(R.id.textViewVillage);
        textViewDistrict = findViewById(R.id.textViewDistrict);
        textViewCity = findViewById(R.id.textViewCity);
        textViewCountry = findViewById(R.id.textViewCountry);


        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                List<Address> addressList;
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                   addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    Log.i("Address",addressList.toString());
                    String latitude = "Latitude : "+location.getLatitude();
                    textViewLatitude.setText(latitude);
                    String longtitude = "longtitude : "+location.getLongitude();
                    textViewLongtitude.setText(longtitude);
                    String Accuracy = "Accuracy : "+location.getAccuracy();
                    textViewAccuracy.setText(Accuracy);
                    String Altitude = "Altitude : "+location.getAltitude();
                    textViewAltitude.setText(Altitude);
                    Address address = addressList.get(0);

                    if (address.getFeatureName()!=null){
                        String village ="Village : " + address.getFeatureName();
                        textViewVillage.setText(village);
                    }
                    else {
                        String village ="Village : Unknown";
                        textViewVillage.setText(village);
                    }

                    if (address.getSubAdminArea()!=null){
                        String district = "District : "+address.getSubAdminArea();
                        textViewDistrict.setText(district);
                    }else {
                        String district = "District : Unknown";
                        textViewDistrict.setText(district);
                    }

                    if (address.getAdminArea()!=null){
                        String city = "City : " + address.getAdminArea();
                        textViewCity.setText(city);
                    }else {
                        String city = "City : Unknown";
                        textViewCity.setText(city);
                    }

                    if (address.getCountryName()!=null){
                        String country = "Country : "+ address.getCountryName();
                        textViewCountry.setText(country);
                    }else {
                        String country = "Country : Unknown";
                        textViewCountry.setText(country);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }

    }
}
