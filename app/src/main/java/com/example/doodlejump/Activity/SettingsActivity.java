package com.example.doodlejump.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.R;

public class SettingsActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

    }

    public void setSoundOn(View view){
      SharedPreferences.Editor editor = Constants.options.edit();
        editor.putBoolean("sound",true);
        editor.apply();
        Constants.mediaPlayer.start();
        Toast.makeText(this,"Sound on", Toast.LENGTH_SHORT).show();
    }

    public void setSoundOff(View view){
        Constants.mediaPlayer.pause();
        SharedPreferences.Editor editor = Constants.options.edit();
        editor.putBoolean("sound",false);
        editor.apply();
        Toast.makeText(this,"Sound off", Toast.LENGTH_SHORT).show();

    }
}
