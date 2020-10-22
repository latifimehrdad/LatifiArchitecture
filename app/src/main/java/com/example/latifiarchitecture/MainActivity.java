package com.example.latifiarchitecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import ir.mlcode.latifiarchitecturelibrary.customs.ML_Toast;
import ir.mlcode.latifiarchitecturelibrary.models.MD_GregorianDate;
import ir.mlcode.latifiarchitecturelibrary.utility.ApplicationUtility;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout test = findViewById(R.id.test);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            ML_Toast.showToast(MainActivity.this, test, "تست زمان توست", getResources().getDrawable(R.drawable.ic_launcher_foreground), getResources().getColor(ir.mlcode.latifiarchitecturelibrary.R.color.mlCollectRight1));
        });
    }
}