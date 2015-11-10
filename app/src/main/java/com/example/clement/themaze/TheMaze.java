package com.example.clement.themaze;

import android.app.Activity;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_the_maze)
public class TheMaze extends Activity implements SensorEventListener {

    @ViewById
    Button buttonSwitchView;
    String interactionDeplacement;
    String interactionSwip;
    int numInteractionDeplacement;
    int numInteractionSwip;
    @ViewById
    MazeView mazeView;

    private boolean map=true;
    @Click
    public void buttonSwitchView(){
        mazeView.setMapView();
        map=!map;
        if (map){
            sensorManager.unregisterListener(this);

        }
        else{
            sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        }
    }

    SensorManager sensorManager;
    Display d;
    private Sensor mAccelerometer;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        d = wm.getDefaultDisplay();

    }


    @AfterViews
    public void initButton(){
        buttonSwitchView.setText("Switch View");
        Intent intent = getIntent();
        interactionDeplacement = intent.getStringExtra("interactionDep");
        interactionSwip = intent.getStringExtra("interactionDep2");
        switch (interactionDeplacement){
            case "Accélérometre":
                numInteractionDeplacement=1;
                break;
            case "onTouch":
                numInteractionDeplacement=2;
                break;
            case "Lancer":
                numInteractionDeplacement=3;
                break;
            case "GesturePad":
                numInteractionDeplacement=4;
                break;
        }
        switch (interactionSwip){
            case "Bouton":
                numInteractionSwip=1;
                break;
            case "Swap":
                numInteractionSwip=2;
                break;
            case "Détection vocale":
                numInteractionSwip=3;
                break;
            case "Remuer":
                numInteractionSwip=4;
                break;
        }
        Log.e("TAG",numInteractionDeplacement+" "+numInteractionSwip);
        Log.e("TAG",interactionDeplacement+" "+interactionSwip);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if (mySensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float x = event.values[0];
            float y = event.values[1];
            //Log.e("TAG", x + " " + y);

            switch(d.getRotation()) {
                case Surface.ROTATION_0:
                    x = event.values[0];
                    y = event.values[1];
                    break;
                case Surface.ROTATION_90:
                    x = -event.values[1];
                    y = event.values[0];
                    break;
                case Surface.ROTATION_180:
                    x = -event.values[0];
                    y = -event.values[1];
                    break;
                case Surface.ROTATION_270:
                    x = event.values[1];
                    y = -event.values[0];
                    break;
            }



                //mazeView.changeXY(x, y);
                mazeView.changeXY(x, y);
                //Log.e("TAG",x+" "+ y);

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);


    }

}
