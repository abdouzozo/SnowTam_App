package com.ensim.snowtam_app.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.ensim.snowtam_app.R;
import com.ensim.snowtam_app.model.InfoAeroport;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SnowtamActivity extends AppCompatActivity {

    private ArrayList<InfoAeroport> ListAeroports;
    private TextView mLog_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snowtam);
        mLog_1 = (TextView) findViewById(R.id.activity_snowtam_log_txt);

        ListAeroports = (ArrayList<InfoAeroport>) getIntent().getSerializableExtra("ListAeroports");

        mLog_1.setText(ListAeroports.get(0).getmId());


        // Output the array
        for(InfoAeroport item:ListAeroports){
            System.out.println(item.getmName());

        }
    }
}
