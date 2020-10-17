package com.example.apssdc.androidsensors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    SensorManager manager;
    List list;
    Sensor proximitySensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SensorEventListener proxlistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY){
                    if (sensorEvent.values[0]==0){
                        tv.setText("NEAR");
                        getWindow().getDecorView().setBackgroundColor(Color.RED);
                    }else{
                        tv.setText("FAR");
                        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        if (proximitySensor==null){
            tv.setText("No Proximity Sensor");
        }else{
                manager.registerListener(proxlistener,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float value[] = sensorEvent.values;
            tv.setText("X : "+value[0]+"\nY : "+value[1]+"\nZ : "+value[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };


    public void asensor(View view) {
        list = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (list.size()>0){
            manager.registerListener(listener,(Sensor)list.get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            tv.setText("No Accelerometer Sensor");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        manager.unregisterListener(listener);
    }

}