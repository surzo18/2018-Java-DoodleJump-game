package com.example.doodlejump.Objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Enemy implements GameObject {

    private Bitmap enemyBitmap;

    public Enemy(Bitmap enemyBitmap){
        this.enemyBitmap = enemyBitmap;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }

    public boolean collisionPlayer(){
            return false;
    };

    public boolean collisionProjectile(){
        return false;
    };
}
