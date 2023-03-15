package com.example.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
int[] ids;
int count = 0;
Thread thread;
Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);
        ids = new int[] {R.layout.layout1,R.layout.layout2,R.layout.layout3,
                R.layout.layout4,R.layout.layout5,R.layout.layout6,R.layout.layout7};
        thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!thread.isInterrupted()) {
                        Thread.sleep(4000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                count = (++count)%ids.length;
                                setContentView(ids[count]);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
            count = (++count)%ids.length;
            setContentView(ids[count]);
        return true;
    }
}