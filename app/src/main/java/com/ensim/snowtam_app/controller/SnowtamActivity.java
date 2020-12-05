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

    private ArrayList<InfoAeroport> ListAeroports;
    public static String snowtamMes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snowtam);
        List<Fragment> list = new ArrayList<>();
        list.add(new mapFragment());
        list.add(new snowtameFragment());
        list.add(new decodeFragment());



        pager = findViewById(R.id.pager);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(),list);

        pager.setAdapter(pagerAdapter);

        ListAeroports = (ArrayList<InfoAeroport>) getIntent().getSerializableExtra("ListAeroports");
        String snowTam = null;
        String TextJson;
        String infoAero = null;

        for(int i=0; i<ListAeroports.size(); i++) {

            try {
                TextJson = getStringFromFile(ListAeroports.get(i).getmId() + ".json");
                fillSnowTam(TextJson, ListAeroports.get(i));
                TextJson = getStringFromFile(ListAeroports.get(i).getmId() + "_info.json");
                fillInfoAero(TextJson, ListAeroports.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }

            snowtamMes = ListAeroports.get(i).getmSnowtam().toString();

        }
        /*Bundle bundle = new Bundle();
        bundle.putString("snowtamMes", snowtamMes);
        snowtameFragment fragsnow = new snowtameFragment();
        fragsnow.setArguments(bundle);*/
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

    //Elle permet de récupérer l'élement du all du snowtam.json
    public String getSnowTam (String TextJson, int id) throws Exception {
        JSONArray jsonArray = new JSONArray(TextJson);
        JSONObject jsonObject = jsonArray.getJSONObject(id);
        return jsonObject.getString("all");
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

    //Elle permet de récupérer l'élement latitude du snow.json
    public String getInfoAero (String TextJson, int id) throws Exception {
        JSONArray jsonArray = new JSONArray(TextJson);
        JSONObject jsonObject = jsonArray.getJSONObject(id);
        return jsonObject.getString("latitude");
    }
}
