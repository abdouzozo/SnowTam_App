package com.ensim.snowtam_app.fragments;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ensim.snowtam_app.R;
import com.ensim.snowtam_app.controller.SnowtamActivity;
import com.ensim.snowtam_app.model.InfoAeroport;

import java.util.ArrayList;

public class snowtameFragment extends Fragment {
    String snowMes;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.snowtam_page,container, false);

        snowMes = SnowtamActivity.snowtamMes;
        TextView text = (TextView) rootView.findViewById(R.id.textView);
        text.setText(snowMes);
        return rootView;
    }
}
