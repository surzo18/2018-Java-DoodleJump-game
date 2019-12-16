package com.example.doodlejump.Activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.context = getApplicationContext();
        Constants.mediaPlayer = new MediaPlayer();


        Intent game = new Intent(this, MenuActivity.class);
        finish();
        startActivity(game);

    }

}
