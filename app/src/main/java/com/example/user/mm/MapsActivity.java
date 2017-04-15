package com.example.user.mm;

import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.Mnemonica.R;
import com.example.user.mm.TimeBetweenLocations.CalculateDistanceTime;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private GPSTracker gps;
    private double curr_longt,curr_lat, act_longt, act_lat;
    private ArrayList<Activity> activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        gps=new GPSTracker(MapsActivity.this);
        activities=CreateAct.activities;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        MultiDex.install(this);

        curr_longt=gps.getLongitude();
        curr_lat=gps.getLatitude();

        AddActToSchedule obj= new AddActToSchedule();
        Activity act=obj.nearestAct();

        act_longt=act.getLongt();
        act_lat=act.getLat();


        LatLng firstLatLng=new LatLng(curr_lat,curr_longt);// current
        LatLng sectLatLng=new LatLng(act_lat,act_longt); // nearest activity





        CalculateDistanceTime distance_task = new CalculateDistanceTime(MapsActivity.this);
        distance_task.getDirectionsUrl(firstLatLng, sectLatLng);

        distance_task.setLoadListener(new CalculateDistanceTime.taskCompleteListener() {
            @Override
            public void taskCompleted(String[] time_distance) {
                Toast.makeText(MapsActivity.this, time_distance[1],Toast.LENGTH_LONG).show();
                Toast.makeText(MapsActivity.this, time_distance[0],Toast.LENGTH_LONG).show();
            }

        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
