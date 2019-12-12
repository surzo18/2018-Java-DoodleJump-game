package com.example.doodlejump.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class JumpRow implements GameObject{

    Point positionPoint = new Point();
    Rect position = new Rect();
    Bitmap jumpRowImage;

    public JumpRow(Bitmap jumpRowImage, int posX, int posY){
        this.jumpRowImage = jumpRowImage;
        this.positionPoint.x = posX;
        this.positionPoint.y = posY;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(jumpRowImage,null,position,null);
    }

    @Override
    public void update() {
        position.set(positionPoint.x - jumpRowImage.getWidth()/2, positionPoint.y - jumpRowImage.getHeight()/2, positionPoint.x + jumpRowImage.getWidth()/2, positionPoint.y + jumpRowImage.getHeight()/2);
    }
}
