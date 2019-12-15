package com.example.bombsubmarinev2;

import android.graphics.Canvas;
import android.view.SurfaceHolder;



public class GameViewDrawThread extends Thread {
    GameView gameView;
    SurfaceHolder holder;
    Boolean isRun;

    Canvas c=null;


    public GameViewDrawThread(GameView gameView, SurfaceHolder holder)
    {
        this.gameView=gameView;
        this.holder=holder;
        isRun=true;
    }

    public void setFlag(boolean flag)
    {
        isRun=flag;
    }

    @Override
    public void run() {
        while (isRun)
        {
            try {
                c=holder.lockCanvas();
                synchronized (holder)
                {
                    if(c!=null)
                    {
                        gameView.doDraw(c);
                    }
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            } finally {
                if(c!=null)
                {
                    holder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}
