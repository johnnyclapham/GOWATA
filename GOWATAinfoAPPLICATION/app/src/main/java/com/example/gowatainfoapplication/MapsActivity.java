package com.example.gowatainfoapplication;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import android.os.Parcelable;

public class MapsActivity extends FragmentActivity implements  OnMapReadyCallback {
    private GoogleMap mMap;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    Location currentLocation;
    //String key = getResources().getString(R.string.googleMapsApiKey);
    private Polyline currentPolyline;
//    final Button button = findViewById(R.id.button);

    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED =
            Arrays.asList(GAP, DASH,GAP,DOT);

    int stops [];




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
//        System.out.println("-------------------");
//        System.out.println(currentLocation);
//        System.out.println("-------------------");

        Toast.makeText(getApplicationContext(),"onCreate Pass", Toast.LENGTH_LONG).show();

    }






    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);

            System.out.println("permissions no granted");

            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    //must run google maps once before on emulator to have a location
                    System.out.println("************************");
                    System.out.println(location);
                    System.out.println("-------------------");

                    currentLocation = location;
                    Toast.makeText(getApplicationContext(),currentLocation.getLatitude()
                    +""+currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment)
                            getSupportFragmentManager().findFragmentById(R.id.map);
                    supportMapFragment.getMapAsync((MapsActivity.this));
                }
            }
        });
    }





    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */




    @Override
    public void onMapReady(final GoogleMap map) {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(

                            this, R.raw.mapstyle));
            Log.e("MapsActivity", "Styled ");

            if (!success) {
                Log.e("MapsActivity", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MapsActivity", "Can't find style. Error: ", e);
        }
        //map.setOnInfoWindowClickListener(MyOnInfoWindowClickListener);


        addStops(map);

        //map.setPadding(300,100,100,0);
        final LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .title("You are here!");
        //map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,13));
        map.addMarker(markerOptions);
        Toast.makeText(getApplicationContext(),"onMapReady Pass", Toast.LENGTH_LONG).show();


        MarkerOptions stop1 = new MarkerOptions().position(new LatLng(32.548020, 92.663290)).title("Location 1");
        MarkerOptions stop2 = new MarkerOptions().position(new LatLng(40.357680, 76.778640)).title("Location 1");





        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener()
        {
            @Override
            public void onMapLongClick(LatLng arg0)
            {
                Toast.makeText(getApplicationContext(),"onMapLongClick Pass", Toast.LENGTH_LONG).show();
//



                android.util.Log.i("onMapClick", "Going to the Burg!");
                LatLng williamsburg = new LatLng(37.2708, -76.7117);
                //map.moveCamera(CameraUpdateFactory.newLatLng(williamsburg));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(williamsburg,14));

//                map.setTrafficEnabled(true);

            }
        });


        final Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                //todo some thing once clicked


               //int stops []; declared at top
                //stops[0] =

                Intent pop = new Intent(MapsActivity.this, popActivity.class);
                MapsActivity.this.startActivity(pop);
            //    startActivity(new Intent(MapsActivity.this,popActivity.class));


            }

        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                Toast.makeText(MapsActivity.this,
                        "Bus stop:\n" +
                                marker.getTitle() + "\n" +"Coordinates:\n"+
                                marker.getPosition().latitude + "\n" +
                                marker.getPosition().longitude,
                        Toast.LENGTH_LONG).show();
                add.setVisibility(View.VISIBLE);

            }});



        map.addPolyline(
                new PolylineOptions()
                        .width(5)
                        .pattern(PATTERN_POLYLINE_DOTTED)
                        .add(
                        new LatLng(37.293542, -76.6864281),
                        new LatLng(37.276470, -76.677502),
                        new LatLng(37.270869, -76.684540),
                        new LatLng(37.250512, -76.664799),
                        new LatLng(37.245046, -76.673382),
                        new LatLng(37.249829, -76.731232),
                        new LatLng(37.268683, -76.742046),
                        new LatLng(37.281660, -76.738442),
                        new LatLng(37.282889, -76.729172),//**
                        new LatLng(37.287943, -76.737755),
                        new LatLng(37.308426, -76.742046),
                        new LatLng( 37.311839, -76.729000),
                        new LatLng(37.284119, -76.712864),
                                new LatLng(37.293542, -76.6864281)))
                        .setColor(Color.MAGENTA);

        final Button showTraffic = (Button) findViewById(R.id.show_traffic);
        showTraffic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                map.setTrafficEnabled(true);

            }

    });

        final Button noTraffic = (Button) findViewById(R.id.no_traffic);
        noTraffic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                map.setTrafficEnabled(false);

            }

        });

        final Button showStops = (Button) findViewById(R.id.show_stops);
        showStops.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                Marker marker = map.addMarker(new MarkerOptions()
                        .position(new LatLng(37.2757537, -76.7876489))
                        .title("San Francisco")
                        .snippet("Population: 776733"));

                //WebView webview = new WebView(this);
                Intent myIntent = new Intent(MapsActivity.this, wataActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | myIntent.FLAG_ACTIVITY_NEW_TASK);
                MapsActivity.this.startActivity(myIntent);

                finish();

            }

        });

        //todo make this take us to the key imageview
        final Button key = (Button) findViewById(R.id.key);
        key.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                Intent showKey = new Intent(MapsActivity.this, keyActivity.class);
                showKey.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | showKey.FLAG_ACTIVITY_NEW_TASK);
                MapsActivity.this.startActivity(showKey);
                // dont finish since its a popup
                //finish();

            }
        });



    }


    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        Drawable vectorDrawable= ContextCompat.getDrawable(context,vectorResId) ;
        vectorDrawable.setBounds(0,0,
                vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas= new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }



    public void addStops(final GoogleMap map){
        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.273163, -76.711752))
                .title("Wawa")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN   ))
                .alpha(0.7f)
                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.busclipart)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.272149, -76.713316))
                .title("Sadler")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN   ))
                .alpha(0.7f)
                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.busclipart)));

        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.277653, -76.716865))
                .title("Brooks St.")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN   ))
                .alpha(0.7f)
                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.busclipart)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.274698, -76.720339))
                .title("Stadium")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN   ))
                .alpha(0.7f)
                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.busclipart)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.272102, -76.719105))
                .title("Cafe")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN   ))
                .alpha(0.7f)
                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.busclipart)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.267902, -76.717732))
                .title("W&M Police")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN   ))
                .alpha(0.7f)
                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.busclipart)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.261993, -76.718644))
                .title("Rolfe Rd. (Ludwell)")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN   ))
                .alpha(0.7f)
                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.busclipart)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.267504, -76.714313))
                .title("Indian Springs Rd.")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN   ))
                .alpha(0.7f)
                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.busclipart)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.268341, -76.712584))
                .title("Cary St.")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN   ))
                .alpha(0.7f)
                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.busclipart)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.269759, -76.709113))
                .title("Taliaferro Hall")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN   ))
                .alpha(0.7f)
                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.busclipart)));
        map.addMarker(new MarkerOptions()
                .position(new LatLng(37.263798, -76.705362))
                .title("W&M Law School")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN   ))
                .alpha(0.7f)
                .icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.busclipart)));
    }



}
