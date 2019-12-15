package com.example.bombsubmarinev2;

import android.app.Service;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class Boat {

    public static int LIFE=3 ;

    MainActivity mainActivity;
    Bitmap boat;
    Paint paint;

    float X=800;
    float Y;

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    public Boat(final MainActivity mainActivity) {
        paint = new Paint();
        paint.setAntiAlias(true);
        this.mainActivity = mainActivity;
        boat = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.ship);

        float y=(float) mainActivity.getScreenH();
        Y=y/20;

        sensorManager=(SensorManager)mainActivity.getSystemService(Service.SENSOR_SERVICE);
        assert sensorManager != null;
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[1]<-0.6)
                {
                    if(X>0)
                        X-=7;
                }
                else if(sensorEvent.values[1]>0.6)
                {
                    if(X<mainActivity.getScreenW()-boat.getWidth())//如果没出右边界
                        X+=7;
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_GAME);
    }

    public void drawSelf(Canvas canvas)
    {
        canvas.drawBitmap(boat,X,Y,paint);
    }

}
