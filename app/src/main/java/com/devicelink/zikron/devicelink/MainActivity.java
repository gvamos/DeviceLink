package com.devicelink.zikron.devicelink;

import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){

            case R.id.action_reset:
                Toast.makeText(getApplicationContext(), "action reset", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_reconnect:
                Toast.makeText(getApplicationContext(), "action reconnect", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "action settings", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    ScreenManager screenManager;

    @Override
    public void onStart(){
        super.onStart();

        screenManager = new ScreenManager(this);
        screenManager.config();
    }

    static class Ticker implements Runnable {

        Thread tickerThread;
        ScreenManager screenManager;

        Ticker(ScreenManager screenManager){
            this.screenManager = screenManager;
            tickerThread = new Thread(this);
        }

        public void init(){ tickerThread.start(); }

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                SystemClock.sleep(250);
                if (i % 5 == 0) {
                    // update UI with progress every 5%
                    screenManager.requestDisplay(i + "% Complete!");
                }
            }
            screenManager.requestDisplay("i Count complete!!!");
        }
    }

    public void onBtnLink(View v) {
        screenManager.requestStatus("onBtnLink 0.1");
        screenManager.requestCommand("default command");
        screenManager.requestDisplay("Screen Manager Initialized 0.1");
        Toast.makeText(getApplicationContext(), "onBtnLink", Toast.LENGTH_LONG).show();
    }

    public void onBtnTest1Click(View v) {
        screenManager.requestStatus("onBtnTest1Click");
        Ticker ticker = new Ticker(screenManager);
        ticker.init();
        Toast.makeText(getApplicationContext(), "onBtnTest1Click", Toast.LENGTH_LONG).show();
    }

    public void onBtnTest2Click(View v) {
        screenManager.requestStatus("onBtnTest2Click");
        Toast.makeText(getApplicationContext(), "onBtnTest2Click", Toast.LENGTH_LONG).show();
    }
}
