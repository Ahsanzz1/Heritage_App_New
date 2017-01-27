package uk.ac.bucks.heritage_app_new;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import java.util.Map;
import java.util.WeakHashMap;

import static uk.ac.bucks.heritage_app_new.R.id.btnInfo;
import static uk.ac.bucks.heritage_app_new.R.id.title;
import static uk.ac.bucks.heritage_app_new.R.id.tvInfo;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    public ImageView icon; // imageview for the icon
    public Button btnInfo;// more info button
    private GoogleMap mMap; //assign the google map
    GoogleApiClient mGoogleApiClient;//assign google maps appi client
    Location mLastLocation;//set lastlocation
    Marker mCurrLocationMarker;//marker for current location
    LocationRequest mLocationRequest;//marker for location request


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // to remove the titlebar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, // to initialize the activity in full screen mode
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_maps);

        icon = (ImageView) findViewById(R.id.icon); // link the imageview icon to xml
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mMap != null) { // if map is loaded execute the code

            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() { // initialize the info window

                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    View v = getLayoutInflater().inflate(R.layout.info_window, null); //link the info window to xml
                    final TextView tvInfo = (TextView) v.findViewById(R.id.tvInfo);// link the info textview in infowindow to xml
                    btnInfo = (Button) v.findViewById(R.id.btnInfo);//link moreinfo button to xml
                    tvInfo.setText(marker.getTitle());//set the title of info window according to the title of marker selected
                    return v;//return view
                }
            });
        }

        // 1 Add a marker in wycombe library and move the camera
        LatLng library = new LatLng(51.630692, -0.755044);
        final Marker lib = mMap.addMarker(new MarkerOptions().position(library).title("Tourist Information Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(library));

        // 2 Add a marker in Hen & Chickens and move the camera
        LatLng HC = new LatLng(51.630600, -0.752569);
        final Marker hc = mMap.addMarker(new MarkerOptions().position(HC).title("Hen & Chickens"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(HC));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                library,
                new LatLng(51.630811, -0.755002),
                new LatLng(51.631051, -0.754893),
                new LatLng(51.631112, -0.754665),
                new LatLng(51.631026, -0.754314),
                new LatLng(51.630899, -0.754302),
                new LatLng(51.631005, -0.753927),
                new LatLng(51.630884, -0.753405),
                new LatLng(51.630709, -0.752732),
                HC)
                .width(20)
                .color(Color.BLUE)
        );

        // 3 Add a marker in War memorial and move the camera
        LatLng stone = new LatLng(51.630214, -0.751591);
        final Marker ST = mMap.addMarker(new MarkerOptions().position(stone).title("War memorial"));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                HC,
                new LatLng(51.630456, -0.752516),
                new LatLng(51.630378, -0.752422),
                new LatLng(51.630312, -0.752145),
                new LatLng(51.630251, -0.751786),
                stone)
                .width(20)
                .color(Color.BLUE)
        );

        // 4 Add a marker in Guildhall stained glass window the camera
        LatLng window = new LatLng(51.629604, -0.751517);
        mMap.addMarker(new MarkerOptions().position(window).title("Guildhall stained glass window"));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                stone,
                new LatLng(51.630125, -0.751550),
                new LatLng(51.629982, -0.751497),
                new LatLng(51.629807, -0.751483),
                window)
                .width(20)
                .color(Color.BLUE)
        );

        // 5 Add a marker in Military camp and move the camera
        LatLng hospital = new LatLng(51.627146, -0.752754);
        mMap.addMarker(new MarkerOptions().position(hospital).title("Military camp"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hospital));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                window,
                new LatLng(51.629458, -0.751561),
                new LatLng(51.629179, -0.751616),
                new LatLng(51.628989, -0.751599),
                new LatLng(51.628765, -0.751388),
                new LatLng(51.628702, -0.751381),
                new LatLng(51.628465, -0.751422),
                new LatLng(51.628393, -0.751490),
                new LatLng(51.628315, -0.751643),
                new LatLng(51.628307, -0.751759),
                new LatLng(51.628253, -0.751928),
                new LatLng(51.628203, -0.752154),
                new LatLng(51.628142, -0.752188),
                hospital)
                .width(20)
                .color(Color.BLUE)
        );

        // 6 Add a marker in Wycombe Abbey and move the camera
        LatLng abbey = new LatLng(51.626706, -0.751302);
        mMap.addMarker(new MarkerOptions().position(abbey).title("Wycombe Abbey"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(abbey));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                hospital,
                new LatLng(51.627075, -0.752222),
                new LatLng(51.627056, -0.752037),
                new LatLng(51.626993, -0.751770),
                new LatLng(51.626951, -0.751707),
                new LatLng(51.626803, -0.751614),
                new LatLng(51.626674, -0.751448),
                abbey)
                .width(20)
                .color(Color.BLUE)
        );

        // 7 Add a marker in War Office railings and move the camera
        LatLng railing = new LatLng(51.624928, -0.746941);
        mMap.addMarker(new MarkerOptions().position(railing).title("War Office railings"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(railing));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                abbey,
                new LatLng(51.626746, -0.751123),
                new LatLng(51.626754, -0.750281),
                new LatLng(51.626746, -0.749892),
                new LatLng(51.626736, -0.749798),
                new LatLng(51.626704, -0.749717),
                new LatLng(51.626709, -0.749623),
                new LatLng(51.626668, -0.749440),
                new LatLng(51.626662, -0.749353),
                new LatLng(51.626689, -0.749076),
                new LatLng(51.626664, -0.748978),
                new LatLng(51.626672, -0.748744),
                new LatLng(51.626695, -0.748575),
                new LatLng(51.626738, -0.748462),
                new LatLng(51.626742, -0.748327),
                new LatLng(51.626749, -0.747657),
                new LatLng(51.626737, -0.747286),
                new LatLng(51.626727, -0.746887),
                new LatLng(51.626434, -0.746590),
                new LatLng(51.626187, -0.746490),
                new LatLng(51.625960, -0.746441),
                new LatLng(51.625765, -0.746456),
                new LatLng(51.625502, -0.746553),
                new LatLng(51.625191, -0.746829),
                railing)
                .width(20)
                .color(Color.BLUE)
        );

        // 8 Add a marker in  Aircraft factories and move the camera
        LatLng tempschool = new LatLng(51.625725, -0.746487);
        mMap.addMarker(new MarkerOptions().position(tempschool).title("Aircraft factories"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tempschool));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                railing,
                new LatLng(51.625196, -0.746834),
                new LatLng(51.625430, -0.746631),
                tempschool)
                .width(20)
                .color(Color.BLUE)
        );

        // 9 Add a marker in Temporary home for girls school and move the camera
        LatLng school = new LatLng(51.627649, -0.744757);
        mMap.addMarker(new MarkerOptions().position(school).title("Boys Grammar school"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(school));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                tempschool,
                new LatLng (51.625843, -0.746456),
                new LatLng (51.625999, -0.746467),
                new LatLng (51.626117, -0.746472),
                new LatLng (51.626239, -0.746492),
                new LatLng (51.626330, -0.746528),
                new LatLng (51.626434, -0.746593),
                new LatLng (51.626555, -0.746707),
                new LatLng (51.626657, -0.746818),
                new LatLng (51.626731, -0.746903),
                new LatLng (51.626736, -0.746895),
                new LatLng (51.626738, -0.746885),
                new LatLng (51.626755, -0.746604),
                new LatLng (51.626767, -0.746420),
                new LatLng (51.626797, -0.745797),
                new LatLng (51.626807, -0.745685),
                new LatLng (51.626775, -0.745047),
                new LatLng (51.626738, -0.744377),
                new LatLng (51.626711, -0.743858),
                new LatLng (51.626721, -0.743799),
                new LatLng (51.626743, -0.743767),
                new LatLng (51.626898, -0.743668),
                new LatLng (51.626928, -0.743622),
                new LatLng (51.627050, -0.743438),
                new LatLng (51.627414, -0.744199),
                new LatLng (51.627474, -0.744341),

                school)
                .width(20)
                .color(Color.BLUE)
        );

        // 10 Add a marker in Station and recruitment office and move the camera
        LatLng office = new LatLng(51.629885, -0.746986);
        mMap.addMarker(new MarkerOptions().position(office).title("Station and recruitment office"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(office));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                school,
                new LatLng (51.627736, -0.745011),
                new LatLng (51.627780, -0.745171),
                new LatLng (51.627847, -0.745591),
                new LatLng (51.627967, -0.746326),
                new LatLng (51.628113, -0.747017),
                new LatLng (51.628381, -0.748181),
                new LatLng (51.628400, -0.748243),
                new LatLng (51.628414, -0.748250),
                new LatLng (51.628432, -0.748231),
                new LatLng (51.628608, -0.748047),
                new LatLng (51.628668, -0.747992),
                new LatLng (51.629485, -0.747279),

                office)
                .width(20)
                .color(Color.BLUE)
        );

        // 11 Add a marker in Mary Christies boarding house and move the camera
        LatLng house = new LatLng(51.630347, -0.746755);
        mMap.addMarker(new MarkerOptions().position(house).title("Mary Christies boarding house"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(house));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                office,
                new LatLng (51.630169, -0.746799),
                new LatLng (51.630322, -0.746656),
                new LatLng (51.630332, -0.746662),

                house)
                .width(20)
                .color(Color.BLUE)
        );

        // 12 Add a marker in unknown and move the camera
        LatLng n = new LatLng(51.630669, -0.748068);
        mMap.addMarker(new MarkerOptions().position(n).title("The Museum"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(n));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                house,
                new LatLng (51.630375, -0.746912),
                new LatLng (51.630454, -0.747295),
                new LatLng (51.630579, -0.747901),
                new LatLng (51.630636, -0.748033),

                n)
                .width(20)
                .color(Color.BLUE)
        );

        // 13 Add a marker in Cemetery and move the camera
        LatLng cementry = new LatLng(51.632390, -0.747907);
        mMap.addMarker(new MarkerOptions().position(cementry).title("Cemetery"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cementry));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                n,
                new LatLng (51.630639, -0.748064),
                new LatLng (51.630627, -0.748015),
                new LatLng (51.630624, -0.747962),
                new LatLng (51.630589, -0.747891),
                new LatLng (51.630586, -0.747871),
                new LatLng (51.630599, -0.747860),
                new LatLng (51.631017, -0.747647),
                new LatLng (51.631923, -0.747255),

                new LatLng (51.632248, -0.747117),
                new LatLng (51.632257, -0.747122),
                new LatLng (51.632266, -0.747134),
                new LatLng (51.632276, -0.747179),

                cementry)
                .width(20)
                .color(Color.BLUE)
        );

        // 14 Add a marker in VAD hospital site and move the camera
        LatLng vad = new LatLng(51.633479, -0.748977);
        mMap.addMarker(new MarkerOptions().position(vad).title("VAD hospital site"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vad));
        //add the line between two markers using the latlngs
        mMap.addPolyline(new PolylineOptions().add(
                cementry,
                new LatLng (51.632431, -0.748231),
                new LatLng (51.632425, -0.748270),
                new LatLng (51.632433, -0.748347),
                new LatLng (51.632451, -0.748538),
                new LatLng (51.632462, -0.748555),
                new LatLng (51.632476, -0.748560),
                new LatLng (51.632584, -0.748521),
                new LatLng (51.633460, -0.748243),
                new LatLng (51.633599, -0.748221),
                new LatLng (51.633707, -0.748215),
                new LatLng (51.633796, -0.748224),
                new LatLng (51.633874, -0.748254),
                new LatLng (51.633968, -0.748312),
                new LatLng (51.634058, -0.748416),
                new LatLng (51.634170, -0.748571),
                new LatLng (51.634237, -0.748711),
                new LatLng (51.634252, -0.748745),
                new LatLng (51.634249, -0.748767),
                new LatLng (51.634240, -0.748780),
                new LatLng (51.634224, -0.748806),
                new LatLng (51.634215, -0.748838),
                new LatLng (51.634210, -0.748851),
                new LatLng (51.634204, -0.748864),
                new LatLng (51.633830, -0.748959),
                new LatLng (51.633788, -0.748966),

                vad)
                .width(20)
                .color(Color.BLUE)
        );

        //Opening new activities depending on the selected marker
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                /*
                sharedpreferences will be used to store an int each time any marker's info window
                is tapped. this will be different for all the markers. on content activity this int
                will be used to check which marker is tapped and the contents will be initialized
                accordingly.
                 */
                SharedPreferences sharedPref = getSharedPreferences("hscoreinfo", Context.MODE_PRIVATE);

                if(marker.getTitle().equals("Tourist Information Center")){

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 1); //set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("Hen & Chickens")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 2);//set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("War memorial")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 3);//set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("Guildhall stained glass window")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 4);//set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("Military camp")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 5);//set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("Wycombe Abbey")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 6); //set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("War Office railings")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 7); //set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("Aircraft factories")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 8);//set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("Boys Grammar school")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 9); //set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("Station and recruitment office")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 10); //set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("Mary Christies boarding house")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 11);//set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("The Museum")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 12); //set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("Cemetery")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 13); //set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }

                if (marker.getTitle().equals("VAD hospital site")) {

                    SharedPreferences.Editor editor = sharedPref.edit(); // edit sharedpreferences
                    editor.putInt("score", 14); //set the value of int
                    editor.commit(); //apply values

                    Intent intent = new Intent(MapsActivity.this, ContentActivity.class);
                    startActivity(intent);
                }
            }
        });

        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Maps Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}
