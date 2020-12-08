package com.ensim.snowtam_app.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import com.ensim.snowtam_app.R;
import com.ensim.snowtam_app.model.InfoAeroport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText mChampsInput_1;
    private EditText mChampsInput_2;
    private EditText mChampsInput_3;
    private EditText mChampsInput_4;
    private Button mSearchButton;

    private ArrayList<InfoAeroport> ListAeroports;

    boolean EmptyChamps_1 = true;
    boolean EmptyChamps_2 = true;
    boolean EmptyChamps_3 = true;
    boolean EmptyChamps_4 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChampsInput_1 = (EditText) findViewById(R.id.activity_main_input_1);
        mChampsInput_2 = (EditText) findViewById(R.id.activity_main_input_2);
        mChampsInput_3 = (EditText) findViewById(R.id.activity_main_input_3);
        mChampsInput_4 = (EditText) findViewById(R.id.activity_main_input_4);


        mSearchButton = (Button) findViewById(R.id.activity_main_search_btn);

        mSearchButton.setEnabled(false);
        mChampsInput_2.setEnabled(false);
        mChampsInput_3.setEnabled(false);
        mChampsInput_4.setEnabled(false);

        ListAeroports = new ArrayList<InfoAeroport>();

        mChampsInput_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EmptyChamps_1 = !(s.toString().length() != 0);
                mSearchButton.setEnabled(!EmptyChamps_1);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!EmptyChamps_1){
                    mChampsInput_2.setEnabled(true);
                }
            }
        });
        mChampsInput_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EmptyChamps_2 = !(s.toString().length() != 0);
                mSearchButton.setEnabled(!EmptyChamps_1);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!EmptyChamps_2){
                    mChampsInput_3.setEnabled(true);
                }
            }
        });
        mChampsInput_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EmptyChamps_3 = !(s.toString().length() != 0);
                mSearchButton.setEnabled(!EmptyChamps_1);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!EmptyChamps_3){
                    mChampsInput_4.setEnabled(true);
                }
            }
        });
        mChampsInput_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EmptyChamps_4 = !(s.toString().length() != 0);
                mSearchButton.setEnabled(!EmptyChamps_1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ouvrir une pop-up
                //openDialog();

                //Ouvrir une activité

                ListAeroports.add(new InfoAeroport(mChampsInput_1.getText().toString()));
                if(!EmptyChamps_2)
                    ListAeroports.add(new InfoAeroport(mChampsInput_2.getText().toString()));
                if(!EmptyChamps_3)
                    ListAeroports.add(new InfoAeroport(mChampsInput_3.getText().toString()));
                if(!EmptyChamps_4)
                    ListAeroports.add(new InfoAeroport(mChampsInput_4.getText().toString()));



                /*Intent responseActivity = new Intent(MainActivity.this, SnowtamActivity.class);
                responseActivity.putExtra("name", Name);
                responseActivity.putExtra("last_name", LName);

                startActivity(responseActivity);*/

                // Construct an Intent object that targets the SecondActivity
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);

                intent.putExtra("ListAeroports",ListAeroports);
                // Start the SecondActivity
                startActivity(intent);

            }
        });
    }


}
