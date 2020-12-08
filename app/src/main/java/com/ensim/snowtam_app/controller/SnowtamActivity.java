package com.ensim.snowtam_app.controller;

import androidx.appcompat.app.AppCompatActivity;
import com.ensim.snowtam_app.R;
import com.ensim.snowtam_app.model.InfoAeroport;
import com.ensim.snowtam_app.model.SnowtamAeroport;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

/*https://applications.icao.int/dataservices/api/indicators-list?api_key=69ca9a70-27ed-11eb-a05a-a74a9cd8306b
&state=&airports=LFPG&format=json
 */


public class SnowtamActivity extends AppCompatActivity {

    private SnowtamAeroport SnowtamClique = new SnowtamAeroport();
    private TextView mLog_1;
    private Button mMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snowtam);
        mLog_1 = (TextView) findViewById(R.id.activity_snowtam_log_txt);
        mMapButton= (Button) findViewById(R.id.activity_snowtam_map_btn);

        String TextJson;

        SnowtamClique.setmId( (String) getIntent().getSerializableExtra("AeroportClique") );
        double[] coord = (double[]) getIntent().getSerializableExtra("CoordClique");

        try {
            TextJson = getStringFromFile(SnowtamClique.getmId() + ".json");
            fillSnowTam(TextJson, SnowtamClique);
        } catch (Exception e) {
            e.printStackTrace();
        }


       /* MonAeroport = (InfoAeroport) getIntent().getSerializableExtra("AeroportClique"); */


            mLog_1.setText(SnowtamClique.toString());
            mLog_1.append("\n\nLat : " + coord[0] + "\nLon : " + coord[1]);

    }


    public void fillSnowTam(String TextJson, SnowtamAeroport snowtam) throws Exception {
        JSONArray jsonArray = new JSONArray(TextJson);
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        snowtam.setmId(jsonObject.getString("id"));
        snowtam.setmSnowtam(jsonObject.getString("all"));
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
