package com.example.doodlejump;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

    DoodleGameView gView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doodle_activity);
        gView = (DoodleGameView) findViewById(R.id.doodleView);
    }

}
