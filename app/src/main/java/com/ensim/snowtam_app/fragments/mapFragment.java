package com.ensim.snowtam_app.fragments;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ensim.snowtam_app.R;
import com.ensim.snowtam_app.controller.MainActivity;
import com.ensim.snowtam_app.controller.MapsActivity;
import com.ensim.snowtam_app.controller.SnowtamActivity;
import com.ensim.snowtam_app.model.InfoAeroport;
import com.ensim.snowtam_app.model.SnowtamAeroport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.ArrayList;

public class mapFragment extends Fragment implements OnMapReadyCallback {

    double[] coord;
    private GoogleMap mMap;
    SnowtamAeroport SnowtamClique;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.map_page,container, false);
        return rootView;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLon;
        latLon = new LatLng(coord[0], coord[1]);
            Marker marker = mMap.addMarker(new MarkerOptions().position(latLon).title(SnowtamClique.getmId()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLon));

    }
}
