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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class mapFragment extends Fragment{

    LatLng latLng;
    private GoogleMap mMap;
    SnowtamAeroport SnowtamClique;
    private SupportMapFragment mapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = (ViewGroup)inflater
                .inflate(R.layout.map_page,container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.mapwhere);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                SnowtamClique = SnowtamActivity.SnowtamClique;
                LatLng latlng = SnowtamActivity.latlng;
                Marker marker = googleMap.addMarker(new MarkerOptions().position(latlng).title(SnowtamClique.getmName()));
                googleMap.setMapType(4);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 14));
            }
        });
        return rootView;
    }
}
