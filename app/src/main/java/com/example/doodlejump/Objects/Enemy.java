package com.example.doodlejump.Objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.R;

import java.lang.reflect.Array;

public class Enemy implements GameObject {

    //Images
    private final Bitmap enemy1     = BitmapFactory.decodeResource(
            Constants.context.getResources(), R.drawable.enemy1
    );
    private final Bitmap enemy2     = BitmapFactory.decodeResource(
            Constants.context.getResources(), R.drawable.enemy2
    );
    private final Bitmap enemy3     = BitmapFactory.decodeResource(
            Constants.context.getResources(), R.drawable.enemy3
    );
    private final Bitmap enemy4     = BitmapFactory.decodeResource(
            Constants.context.getResources(), R.drawable.enemy4
    );

    private Bitmap enemyCurrentImg;
    private int currentImgNumber = 0;
    private Bitmap []animationImg = new Bitmap[4];

    private Point position;
    private Rect enemyBox;
    private int speed  = 10;


    public Enemy(Point position){
        this.position = position;

        this.animationImg[0] = enemy1;
        this.animationImg[1] = enemy2;
        this.animationImg[2] = enemy3;
        this.animationImg[3] = enemy4;

        this.enemyCurrentImg = animationImg[currentImgNumber];
        this.enemyBox = new Rect(position.x - enemyCurrentImg.getWidth()/2,position.y - enemyCurrentImg.getHeight()/2,position.x + enemyCurrentImg.getWidth()/2, position.y + enemyCurrentImg.getHeight()/2);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(enemyCurrentImg,null,enemyBox,null);
    }

    @Override
    public void update() {

        if(this.position.x >= (Constants.SCREEN_WIDTH - this.enemyCurrentImg.getWidth()/2)){
            speed = -10;
        }
        else if(this.position.x < 0 + this.enemyCurrentImg.getWidth()/2){
            speed = 10;
        }

        this.position.x += speed;


        currentImgNumber += 1;
        if(currentImgNumber == 4){
            currentImgNumber = 0;
        }
        this.enemyCurrentImg = animationImg[currentImgNumber];

        this.enemyBox = new Rect(position.x - enemyCurrentImg.getWidth()/2,position.y - enemyCurrentImg.getHeight()/2,position.x + enemyCurrentImg.getWidth()/2, position.y + enemyCurrentImg.getHeight()/2);

    }
}
