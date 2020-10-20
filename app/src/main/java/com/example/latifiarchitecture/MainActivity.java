package com.example.latifiarchitecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import ir.mlcode.latifiarchitecturelibrary.models.MD_GregorianDate;
import ir.mlcode.latifiarchitecturelibrary.utility.ApplicationUtility;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApplicationUtility utility = new ApplicationUtility();
        MD_GregorianDate geo = utility.solarDate_to_gregorian("1367/11/31");
        Toast.makeText(this, "date : " + geo.getDateString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "date : " + geo.getDate(), Toast.LENGTH_SHORT).show();
    }
}