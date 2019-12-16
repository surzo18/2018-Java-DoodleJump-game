package com.example.doodlejump.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.R;

public class SettingsActivity extends Activity {

    private ImageButton skin1;
    private ImageButton skin2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

        final ImageButton skin1 = (ImageButton) findViewById(R.id.Skin1);
        final ImageButton skin2 = (ImageButton) findViewById(R.id.Skin2);

        skin1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               setSkin(1);
            }
        });

        skin2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setSkin(2);
            }
        });

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

    public void setSkin(int skinNumber){
        SharedPreferences.Editor editor = Constants.options.edit();
        editor.putInt("skin",skinNumber);
        editor.apply();

        if(skinNumber == 1){
            Toast.makeText(this,"Normal skin selected", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Jungle skin selected", Toast.LENGTH_SHORT).show();
        }
    }

}
