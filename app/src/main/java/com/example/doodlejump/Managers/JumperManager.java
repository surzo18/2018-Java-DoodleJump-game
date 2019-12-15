package com.example.doodlejump.Managers;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.Objects.GameObject;
import com.example.doodlejump.Objects.Jumper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JumperManager implements GameObject {

    private List<Jumper> jumpers;
    private Point lastCreatedPlatformPosition;

    private CollisionManager collisionManager;

    public JumperManager() {
        this.jumpers = new ArrayList<>();
        this.collisionManager = new CollisionManager();
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < jumpers.size(); i++) {
            jumpers.get(i).draw(canvas);
        }
    }

    @Override
    public void update() {
        this.GenerateJumper();

        for (int i = 0; i < jumpers.size(); i++) {
            jumpers.get(i).update();
        }

        this.destroyBlocks();
    }

    public void addJumper(Jumper jumper) {
        this.jumpers.add(jumper);
        this.lastCreatedPlatformPosition = jumper.getJumperPosition();
    }

    public Jumper getJumper(int i) {
        return jumpers.get(i);
    }

    public int getSize() {
        return this.jumpers.size();
    }

    public void updatePlatformsY(int diff) {
        for (int i = 0; i < jumpers.size(); i++) {
            jumpers.get(i).minusY(diff);
        }

    }

    // 80 is half of width jumper
    public void GenerateJumper() {
        if (this.lastCreatedPlatformPosition.y >= 300) {
            int count = getRandomNumberInRange(1, 3);
            for (int i = 0; i < count; i++) {

                boolean create = false;
                Jumper newJumper = null;
                int x = generateX();

                while (!create) {
                    create = true;
                    newJumper = new Jumper(new Point(x, -100));

                    for (int j = 0; j <= i; j++) {
                        Jumper jumperForCompare = jumpers.get(jumpers.size() - 1 - j);
                        if (collisionManager.isCollideJumperJumperOnX(newJumper, jumperForCompare)) {
                            create = false;
                            x += 100;
                            if (x > Constants.SCREEN_WIDTH - 80) {
                                x = 80;
                            }
                        }
                    }


                }
                this.addJumper(newJumper);
            }

        }
    }

    private int generateX() {
        return getRandomNumberInRange(80, Constants.SCREEN_WIDTH - 80);
    }

    private int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private void destroyBlocks(){
        for(int i = 0; i < jumpers.size(); i++){
            if(jumpers.get(i).getJumperPosition().y > Constants.SCREEN_HEIGHT + 50){
                jumpers.remove(i);
                //Log.d("Destroy size is:", String.valueOf(jumpers.size()));
            }
        }
    }

}
