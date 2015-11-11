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
    int numLabyrinthe;
    GesturPad gestures;
    @ViewById
    MazeView mazeView;

    private boolean map=true;
    @Click
    public void buttonSwitchView(){
        switchView();
    }
    public void switchView(){
        mazeView.setMapView();
        map=!map;
        if (map){
            if (numInteractionDeplacement==1)
                sensorManager.unregisterListener(this);

        }
        else{
            if (numInteractionDeplacement==1)
                sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        }
    }

    SensorManager sensorManager;
    Display d;
    private Sensor mAccelerometer;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
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
                sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                d = wm.getDefaultDisplay();
                break;
            case "onTouch":
                numInteractionDeplacement=2;
                mazeView.activeOnTouche();
                break;
            case "Lancer":
                numInteractionDeplacement=3;
                mazeView.activeLaunch();
                break;
            case "GesturePad":
                numInteractionDeplacement=4;
                View v = findViewById(R.id.control);
                gestures = new GesturPad(this, v, this);
                v.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        gestures.onTouch(view, motionEvent);
                        return true;
                    }
                });
                break;
        }
        switch (interactionSwip){

            case "Labyrinthe 1":
                numLabyrinthe=1;
                mazeView.setNumLabyrinthe(numLabyrinthe);

                Log.e("Second ou first?:", "" + numLabyrinthe);
                break;
            case "Labyrinthe 2":
                numLabyrinthe=2;
                mazeView.setNumLabyrinthe(numLabyrinthe);
                break;
        }

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



                //theMaze.changeXY(x, y);
                mazeView.changeXY(x, y);
                //Log.e("TAG",x+" "+ y);

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    protected void onResume() {
        super.onResume();
        if (numInteractionDeplacement==1&&!map)
            sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }
    protected void onPause() {
        super.onPause();
        if (numInteractionDeplacement==1)
            sensorManager.unregisterListener(this);
    }

}
