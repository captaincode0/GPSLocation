package com.example.captaincode.gpslocation;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText cordinates;
    private Button getlocation, btnvwlocation;
    private Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cordinates = (EditText) findViewById(R.id.editText);
        this.getlocation = (Button) findViewById(R.id.getlocation);
        this.btnvwlocation = (Button) findViewById(R.id.btnvwlocation);

        this.getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSService gps = new GPSService(getBaseContext());
                gps.getLocation();
                cordinates.setText(gps.result());
                location = gps.getObjectLocation();
            }
        });

        this.btnvwlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });
    }
}
