package com.devicelink.zikron.devicelink;

import android.app.Activity;
import android.content.Intent;
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

    @Override
    public void onStart(){
        super.onStart();
        init();
    }

    TextView textViewStatus;
    TextView textViewResult;
    EditText editTextCommand;

    Button buttonTest1;
    Button buttonTest2;
    Button buttonTest3;
    Button buttonTest4;
    void init(){

        textViewStatus = (TextView)findViewById(R.id.view_status);
        textViewResult = (TextView)findViewById(R.id.view_results);
        editTextCommand = (EditText)findViewById(R.id.edit_command);

        buttonTest1 = (Button)findViewById(R.id.btn_test1);
        buttonTest2 = (Button)findViewById(R.id.btn_test2);
        buttonTest3 = (Button)findViewById(R.id.btn_test3);
        buttonTest4 = (Button)findViewById(R.id.btn_test4);

        textViewStatus.setText("view init");

    }


    static class Poster implements Runnable {

        TextView tv;
        String text;

        Poster(TextView textView, String text){
            tv = textView;
            this.text = text;
        }

        @Override
        public void run() {
            tv.setText(text);
        }
    }

    static class Ticker implements Runnable {

        int fieldCode;
        TextView tv;
        Activity activity;
        ScreenManager screenManager;

        Ticker(ScreenManager screenManager){
            this.screenManager = screenManager;
        }

        @Override
        public void run() {

            int i = 0;

            while (i < 100) {
                SystemClock.sleep(250);
                i++;

                final int curCount = i;
                if (curCount % 5 == 0) {
                    // update UI with progress every 5%
                     screenManager.request(curCount + "% Complete!");

                }
            }
            screenManager.request("Count complete!");
        }
    }

    ScreenManager screenManager = new ScreenManager(this);
    Ticker ticker = new Ticker(screenManager);

    public void onBtnTest1Click(View v) {
        screenManager.config();
        Thread screenManagerThread = new Thread(screenManager);
        screenManager.request("Screen Manager Request submitted");
        screenManagerThread.start();
        Thread tickerThread = new Thread(ticker);
        tickerThread.start();
        Toast.makeText(getApplicationContext(), "onBtnTest1Click", Toast.LENGTH_LONG).show();
    }

    public void onBtnTest2Click(View v) {
        textViewStatus.setText("onBtnTest2Click");
        Toast.makeText(getApplicationContext(), "onBtnTest2Click", Toast.LENGTH_LONG).show();
    }
}
