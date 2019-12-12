package com.example.doodlejump;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class RectPlayer implements  GameObject{

    private Rect rectangle;
    private int color;

    public RectPlayer(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);
    }

    @Override
    public void update() {

    }

    public void update(Point point){
        Log.d("LEFT: ", String.valueOf(point.x - rectangle.width()/2));
        Log.d("TOP: ", String.valueOf(point.y - rectangle.height()/2));
        Log.d("RIGHT: ", String.valueOf(point.x + rectangle.width()/2));
        Log.d("BOTTOM: ", String.valueOf(point.y + rectangle.height()/2));
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
}
