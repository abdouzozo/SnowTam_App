package com.ensim.snowtam_app.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ensim.snowtam_app.R;
import com.ensim.snowtam_app.fragments.decodeFragment;
import com.ensim.snowtam_app.fragments.mapFragment;
import com.ensim.snowtam_app.fragments.snowtameFragment;
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
import java.util.List;

/*https://applications.icao.int/dataservices/api/indicators-list?api_key=69ca9a70-27ed-11eb-a05a-a74a9cd8306b
&state=&airports=LFPG&format=json
 */

public class SnowtamActivity extends AppCompatActivity {
    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private static SnowtamAeroport SnowtamClique = new SnowtamAeroport();
    private TextView aerop_name;
    private String nameClique;
    private Button mMapButton;
    public static String snowtamMes;
    public double[] coord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snowtam);

        List<Fragment> list = new ArrayList<>();
        list.add(new snowtameFragment());
        list.add(new decodeFragment());
        list.add(new mapFragment());

        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(),list);
        aerop_name = findViewById(R.id.Aero_name);
        pager.setAdapter(pagerAdapter);

        String TextJson;
        SnowtamClique.setmId( (String) getIntent().getSerializableExtra("AeroportClique") );
        coord = (double[]) getIntent().getSerializableExtra("CoordClique");
        nameClique = (String) getIntent().getSerializableExtra("NameClique");
        try {
            TextJson = getStringFromFile(SnowtamClique.getmId() + ".json");
            fillSnowTam(TextJson, SnowtamClique);
            snowtamMes = SnowtamClique.getmSnowtam();
            snowtamMes = snowtamMes + "\n\nLat : " + coord[0] + "\nLon : " + coord[1];
            aerop_name.setText(nameClique);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*Bundle bundle = new Bundle();
        bundle.putString("snowtamMes", snowtamMes);
        snowtameFragment fragsnow = new snowtameFragment();
        fragsnow.setArguments(bundle);*/

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
