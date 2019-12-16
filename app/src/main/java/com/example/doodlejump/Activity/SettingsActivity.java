package com.example.doodlejump.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.doodlejump.R;

public class SettingsActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

    }

    public void setSoundOn(View view){
        Toast.makeText(this,"Sound on", Toast.LENGTH_SHORT).show();

    }

    public void setSoundOff(View view){
        Toast.makeText(this,"Sound off", Toast.LENGTH_SHORT).show();

    }
}
