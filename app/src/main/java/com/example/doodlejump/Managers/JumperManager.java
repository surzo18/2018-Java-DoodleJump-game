package com.example.doodlejump.Managers;

import android.graphics.Canvas;
import android.graphics.Point;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.Objects.GameObject;
import com.example.doodlejump.Objects.Jumper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JumperManager implements GameObject {

    private List<Jumper> jumpers;
    private Point lastCreatedPlatformPosition;

    public JumperManager(){
        this.jumpers = new ArrayList<>();
    }

    @Override
    public void draw(Canvas canvas) {
        for(int i = 0; i < jumpers.size();i++){
            jumpers.get(i).draw(canvas);
        }
    }

    @Override
    public void update() {
        this.GenerateJumper();

        for(int i = 0; i < jumpers.size();i++){
            jumpers.get(i).update();
        }
    }

    public void addJumper(Jumper jumper){
        this.jumpers.add(jumper);
        this.lastCreatedPlatformPosition = jumper.getJumperPosition();
    }

    public Jumper getJumper(int i){
        return jumpers.get(i);
    }

    public int getSize(){
        return this.jumpers.size();
    }

    public void updatePlatformsY(int diff) {
        for(int i = 0; i < jumpers.size();i++){
            jumpers.get(i).minusY(diff);
        }

    }

    // 80 is half of width jumper
    public void GenerateJumper(){
        int x = getRandomNumberInRange( 80, Constants.SCREEN_WIDTH - 80);

        if( this.lastCreatedPlatformPosition.y >= 100){
            this.addJumper(new Jumper(new Point(x, - 200)));
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
