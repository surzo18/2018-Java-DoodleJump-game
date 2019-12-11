package com.example.doodlejump;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    private double avarageFPS;
    private SurfaceHolder surfaceHolder;
    private DoodleGameView doodleGameView;
    private boolean running;
    private static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder,DoodleGameView doodleGameView){
        super();
        this.surfaceHolder = surfaceHolder;
        this.doodleGameView = doodleGameView;
    }

    public void setRunning(Boolean running){
        this.running = running;
    }

    @Override
    public void run(){
        long startime;
        long timeMillis = 100/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(running){
            startime = System.nanoTime();
            canvas = null;

            try{
        canvas = this.surfaceHolder.lockCanvas();
        synchronized (surfaceHolder){
            this.doodleGameView.update();
            this.doodleGameView.draw(canvas);
        }

            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas );
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startime)/1000000;
            waitTime = targetTime - timeMillis;
            try{
                if(waitTime > 0)
                    this.sleep(waitTime);
            } catch (Exception e){
                e.printStackTrace();
            }

            totalTime +=System.nanoTime() - startime;
            frameCount++;
            if(frameCount == MAX_FPS){
                avarageFPS = 1000/(totalTime/frameCount/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println(avarageFPS);
            }
        }
    }
}
