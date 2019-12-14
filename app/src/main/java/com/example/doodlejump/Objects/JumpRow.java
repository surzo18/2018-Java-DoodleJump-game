package com.example.doodlejump.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.Timer;
import java.util.TimerTask;

public class JumpRow implements GameObject{

    Point positionPoint = new Point();
    Rect coliderBox = new Rect();
    Bitmap jumpRowImage;



    private int miliseconds = 0;
    private int moveBoxAboutY;

    public JumpRow(Bitmap jumpRowImage, int posX, int posY){
        this.jumpRowImage = jumpRowImage;
        this.positionPoint.x = posX;
        this.positionPoint.y = posY;

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(jumpRowImage,null,coliderBox,null);
    }

    @Override
    public void update() {
        coliderBox.set(positionPoint.x - jumpRowImage.getWidth()/2, positionPoint.y - jumpRowImage.getHeight()/2, positionPoint.x + jumpRowImage.getWidth()/2, positionPoint.y + jumpRowImage.getHeight()/2);
    }


    public Rect getColiderBox() {
        return coliderBox;
    }

    public void setY(int i){
        this.positionPoint.y += i;
    }

    public int getY(){
        return this.positionPoint.y;
    }

}
