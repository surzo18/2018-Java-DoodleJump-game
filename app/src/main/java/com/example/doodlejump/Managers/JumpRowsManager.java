package com.example.doodlejump.Managers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.Objects.GameObject;
import com.example.doodlejump.Objects.JumpRow;
import com.example.doodlejump.R;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class JumpRowsManager implements GameObject {
    private ArrayList<JumpRow> jumpRows;
    private Bitmap jumpRowImage;

    private int speed = 25;
    private int moveBoxAboutY;
    private int miliseconds;

    private int lastYofJumpRow;

    public JumpRowsManager(){
        jumpRows = new ArrayList<>();
        jumpRowImage = Bitmap.createBitmap(
                BitmapFactory.decodeResource(
                        Constants.context.getResources(),
                        R.drawable.gametiles),
                0,
                0,
                160,
                50,
                null,
                true
        );
        this.lastYofJumpRow = Constants.SCREEN_HEIGHT - 1200;
/*

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                miliseconds += 1;
            }
        }, 0, 1);
*/
    }

    public void createObstacle(int x, int y){
        JumpRow jumpRow = new JumpRow(jumpRowImage,x,y);
        jumpRows.add(jumpRow);
    }

    @Override
    public void draw(Canvas canvas) {
        for(int i = 0; i < jumpRows.size(); i++){

            jumpRows.get(i).draw(canvas);

        }
    }

    @Override
    public void update(){
        for(int i = 0; i < jumpRows.size(); i++){
            jumpRows.get(i).update();
        }
    }

    public int getSize(){
        return this.jumpRows.size();
    }

    public JumpRow getJumpRow(int i){
        return this.jumpRows.get(i);
    }

    public void  movePlatform(int y){
        Log.d("Moving all platforms by", String.valueOf(y));
        for(int i = 0; i < jumpRows.size(); i++){

            jumpRows.get(i).setY(y);

        }

        this.lastYofJumpRow -= y;
    }

    public void generate(){
        int r = getRandomNumberInRange(0 + 80,Constants.SCREEN_WIDTH - 80);
        if(Constants.SCREEN_HEIGHT/2 - abs(lastYofJumpRow)  < 400){
            JumpRow jumpRow = new JumpRow(jumpRowImage,r, -200);
            jumpRows.add(jumpRow);
            lastYofJumpRow = jumpRow.getY();
        }
    }


    private int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
