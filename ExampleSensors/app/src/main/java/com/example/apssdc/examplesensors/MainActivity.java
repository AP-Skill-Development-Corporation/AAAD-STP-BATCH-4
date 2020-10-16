package com.example.apssdc.examplesensors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.github.nisrulz.sensey.LightDetector;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.ShakeDetector;
import com.github.nisrulz.sensey.TouchTypeDetector;

public class MainActivity extends AppCompatActivity {
    Switch s1,s2,s3;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1 = findViewById(R.id.ssensor);
        s2 = findViewById(R.id.lsensor);
        s3 = findViewById(R.id.tsensor);
        tv = findViewById(R.id.sd);
        /*Initialise the sensor*/
        Sensey.getInstance().init(MainActivity.this);

        /*Shake Sensor*/
        final ShakeDetector.ShakeListener listener = new ShakeDetector.ShakeListener() {
            @Override
            public void onShakeDetected() {
                tv.setText("Shake Sensor Detected");
            }

            @Override
            public void onShakeStopped() {
            tv.setText("Shake Sensor Stopped");
            }
        };

        /*Implement on click event for switch*/
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Sensey.getInstance().startShakeDetection(listener);
                }
                else{
                    Sensey.getInstance().stopShakeDetection(listener);
                }
            }
        });

        /*Light Sensor*/

        final LightDetector.LightListener lightListener = new LightDetector.LightListener() {
            @Override
            public void onDark() {
                tv.setText("It is Dark Room");
            }

            @Override
            public void onLight() {
                tv.setText("It is Light Room");
            }
        };

        /*Implement on click listener for light sensor switch*/
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Sensey.getInstance().startLightDetection(lightListener);
                }else{
                    Sensey.getInstance().stopLightDetection(lightListener);
                }
            }
        });

        /*Touch Sensor*/
        final TouchTypeDetector.TouchTypListener typListener = new TouchTypeDetector.TouchTypListener() {
            @Override
            public void onDoubleTap() {
                tv.setText("Double Tap");
            }

            @Override
            public void onLongPress() {
                tv.setText("Long Press");
            }

            @Override
            public void onScroll(int i) {
                tv.setText("Scroll");
            }

            @Override
            public void onSingleTap() {
                tv.setText("Single Tap");
            }

            @Override
            public void onSwipe(int i) {
                tv.setText("swipe");
            }

            @Override
            public void onThreeFingerSingleTap() {
                tv.setText("Three fingers Single Tap");
            }

            @Override
            public void onTwoFingerSingleTap() {
                tv.setText("Two Finget Single Tap");
            }
        };

        /*Implement on click listener for touch sensor switch*/
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Sensey.getInstance().startTouchTypeDetection(MainActivity.this,
                            typListener);
                }
                else{
                    Sensey.getInstance().stopTouchTypeDetection();
                }
            }
        });
        }

    public boolean dispatchTouchEvent(MotionEvent me){
        Sensey.getInstance().setupDispatchTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Sensey.getInstance().stop();
    }

}