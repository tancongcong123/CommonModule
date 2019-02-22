package com.jx.commonmodule;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView textview;
    private Timer timer;
    private int count = 0;
    private Thread thread;
    private HandlerInner handlerInner;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                count++;
                textview.setText(""+ count);
            }
        }
    };

    private class HandlerInner extends Handler{
        public HandlerInner(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                //TODO
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.textview);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 1000);
        thread = new Thread(){
            @Override
            public void run() {
//                Looper.prepare();
//                handlerInner = new HandlerInner(Looper.myLooper());
//                handlerInner.sendEmptyMessage(1);
//                Looper.loop();
                handlerInner = new HandlerInner(getMainLooper());
                handlerInner.sendEmptyMessage(1);
            }
        };
        thread.start();
    }
}
