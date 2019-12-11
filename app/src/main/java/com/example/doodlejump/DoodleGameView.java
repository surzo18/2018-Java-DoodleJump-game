package com.example.doodlejump;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class DoodleGameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    Bitmap[] bmp;
    private RectPlayer player;
    private Point playerPoint;

    public DoodleGameView(Context context) {
        super(context);
        //setBackgroundResource(R.drawable.bck);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(),this);

        player = new RectPlayer(new Rect(100,100,200,200), Color.rgb(255,0,0));
        playerPoint = new Point(200,200);
        setFocusable(true);
       // init(context);
    }

    private void init(Context context){
        bmp = new Bitmap[1];
        bmp[0] =  Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.gametiles), 0, 0, 200, 50, null, true);
        bmp[1] =  Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.gametiles), 0, 0, 200, 50, null, true);
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(),this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //NOTHING
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(true ){
            try{
                thread.setRunning(false);
                thread.join();
            } catch (Exception e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /*
    @Override
    protected void onDraw(Canvas canvas){

        for(int i = 0; i< jumps.length; i++){
            //canvas.drawBitmap(bmp[0], null, new Rect(jumps[i].getPosX(),jumps[0].getPosY(),200,50) , null);
        }
        //canvas.drawBitmap(bmp[0],null, new Rect(0,0,10,10),null);
    }
    */

    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                playerPoint.set((int)event.getX(),(int)event.getY());
        }

        return true;
    }

    public void update(){
        player.update(playerPoint);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        player.draw(canvas);

    }
}
