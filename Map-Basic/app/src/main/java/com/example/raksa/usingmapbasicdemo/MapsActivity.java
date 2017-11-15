package com.example.raksa.usingmapbasicdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Everest Mountain and move the camera
        LatLng mountEverestLocation = new LatLng(27.9878547, 86.922832);

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.addMarker(new MarkerOptions().position(mountEverestLocation).title("Mount Everest")
                .icon(BitmapDescriptorFactory.fromBitmap(createMarker(R.drawable.red_marker,150,150))));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mountEverestLocation));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mountEverestLocation,10));

    }


    private Bitmap createMarker(int DrawableID , int width , int height){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(DrawableID);
        Bitmap bitmapResource = bitmapDrawable.getBitmap();
        Bitmap bitmap = Bitmap.createScaledBitmap(bitmapResource,width,height,false);
        return bitmap;
    }

}
