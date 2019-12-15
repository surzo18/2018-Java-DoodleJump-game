package com.example.doodlejump.Objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.example.doodlejump.GameComponents.Constants;
import com.example.doodlejump.R;

public class Jumper implements GameObject {

    //Images
    private final Bitmap jumper = Bitmap.createBitmap(
            BitmapFactory.decodeResource( Constants.context.getResources(),R.drawable.gametiles),
            0,
            0,
            160,
            50,
            null,
            true
    );

    //ColiderBox
    private Rect jumperColliderBox;
    private Point jumperPosition;
    private int jumperImgWidth;
    private int jumperImgHeight;


    public Jumper(Point position){
        this.jumperImgWidth  = jumper.getWidth();
        this.jumperImgHeight = jumper.getHeight();

        this.jumperPosition = position;
        this.setJumperColliderBox(jumperPosition);
    }

    @Override
    public void draw(Canvas canvas) {
        /*
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawRect(jumperColliderBox,paint);
        */
        canvas.drawBitmap(jumper,null,jumperColliderBox,null);
    }

    @Override
    public void update() {

    }

    private void setJumperColliderBox(Point jumperPosition) {
        this.jumperColliderBox = new Rect(
          jumperPosition.x - jumperImgWidth/2,
          jumperPosition.y - jumperImgHeight/2,
          jumperPosition.x + jumperImgWidth/2,
          jumperPosition.y + jumperImgHeight/2
        );
    }

    public Rect getJumperColliderBox(){
        return this.jumperColliderBox;
    }
}
