package com.example.doodlejump.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.View.Game;

public class GameActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);


        this.game = new Game(this);
        setContentView(game);
    }

    public void goMenu() {

        Intent menu = new Intent(Constants.context, MenuActivity.class);
        finish();
        startActivity(menu);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0]; //X

        if(x > 0){
            game.motionLeft();
        }
        if(x < 0){
            game.motionRight();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d("akuracy","Svet");
    }
}
