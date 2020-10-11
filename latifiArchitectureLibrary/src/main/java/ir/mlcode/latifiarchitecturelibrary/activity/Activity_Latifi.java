package ir.mlcode.latifiarchitecturelibrary.activity;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class Activity_Latifi extends AppCompatActivity {

    //______________________________________________________________________________________________ attachBaseContext
    public void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    //______________________________________________________________________________________________ attachBaseContext

}
