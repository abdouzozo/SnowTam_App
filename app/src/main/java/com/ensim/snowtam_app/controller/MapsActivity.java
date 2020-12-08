package com.ensim.snowtam_app.controller;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

import com.ensim.snowtam_app.model.InfoAeroport;
import com.ensim.snowtam_app.model.SnowtamAeroport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ensim.snowtam_app.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<InfoAeroport> ListAeroports;
    private HashMap<String, InfoAeroport> mMarkerMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ListAeroports = (ArrayList<InfoAeroport>) getIntent().getSerializableExtra("ListAeroports");
        String TextJson;
        for(int i=0; i<ListAeroports.size(); i++) {

            try {
                TextJson = getStringFromFile(ListAeroports.get(i).getmId() + ".json");
                fillSnowTam(TextJson, ListAeroports.get(i));
                TextJson = getStringFromFile(ListAeroports.get(i).getmId() + "_info.json");
                fillInfoAero(TextJson, ListAeroports.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng coord;

        for(int i=0; i<ListAeroports.size(); i++){
            coord = new LatLng(Double.valueOf(ListAeroports.get(i).getmCoordLat())
                    ,Double.valueOf(ListAeroports.get(i).getmCoordLon()));
            Marker marker = mMap.addMarker(new MarkerOptions().position(coord).title(ListAeroports.get(i).getmName()));
            mMarkerMap.put(marker.getId(),ListAeroports.get(i));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coord));
        }


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                InfoAeroport AeroportClique = mMarkerMap.get(marker.getId());
                double[] coord = {Double.parseDouble(AeroportClique.getmCoordLat()),
                        Double.parseDouble(AeroportClique.getmCoordLon())};

                Intent intent = new Intent(MapsActivity.this, SnowtamActivity.class);
                intent.putExtra("AeroportClique", (Serializable) AeroportClique.getmId());
                intent.putExtra("CoordClique", (Serializable) coord);
                intent.putExtra("NameClique", (Serializable) AeroportClique.getmName());
                startActivity(intent);
                return false;
            }
        });

    }


    public void fillInfoAero(String TextJson, InfoAeroport Aeroport) throws Exception {
        JSONArray jsonArray = new JSONArray(TextJson);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        Aeroport.setmId(jsonObject.getString("airportCode"));
        Aeroport.setmName(jsonObject.getString("airportName"));
        Aeroport.setmCoordLat(jsonObject.getString("latitude"));
        Aeroport.setmCoordLon(jsonObject.getString("longitude"));
    }

    public void fillSnowTam(String TextJson, InfoAeroport Aeroport) throws Exception {
        JSONArray jsonArray = new JSONArray(TextJson);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        SnowtamAeroport snowtam = new SnowtamAeroport();
        snowtam.setmId(jsonObject.getString("id"));
        snowtam.setmSnowtam(jsonObject.getString("all"));
        Aeroport.setmSnowtam(snowtam);
    }

    // Elle permet de récupérer le contenu de Json en String
    public String getStringFromFile (String filePath) throws Exception {
        InputStream fin = getAssets().open(filePath);
        int size = fin.available();
        byte[] buffer = new byte[size];
        fin.read(buffer);
        fin.close();
        return new String(buffer,"UTF-8");
    }

}
