package com.devicelink.zikron.devicelink;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by gvamos on 4/15/15.
 */
public class ScreenManager extends CommandManager {

    static class Poster implements Runnable {

        TextView tv;
        String text;

        Poster(TextView textView, String text){
            tv = textView;
            this.text = text;
        }

        @Override
        public void run() { tv.setText(text); }
    }


    public static class ScreenCommand extends ManagedCommand {

        int resourceId;
        String message;
        Activity activity;

        public ScreenCommand(Activity activity, int resourceId, String message){
            this.activity = activity;
            this.resourceId = resourceId;
            this.message = message;
        }
        @Override
        public void exec(){
            TextView tv = (TextView)activity.findViewById(resourceId);
            tv.post(new Poster(tv,message));
        }

    }

    Activity activity;
    TextView textViewStatus;
    TextView textViewResult;
    EditText editTextCommand;

    public ScreenManager(Activity activity){

        this.activity = activity;
    }

    public void config(){
        textViewStatus = (TextView)activity.findViewById(R.id.view_status);
        textViewResult = (TextView)activity.findViewById(R.id.view_results);
        editTextCommand = (EditText)activity.findViewById(R.id.edit_command);

        //textViewStatus.setText("view init");
        requestDisplay("ScreenManager init called");
        init();
    }

    public void requestDisplay(String message){
        ScreenCommand command = new ScreenCommand(activity, R.id.view_results,message);
        request(command);
    }

    public void requestStatus(String message){
        ScreenCommand command = new ScreenCommand(activity, R.id.view_status,message);
        request(command);
    }

    public void requestCommand(String message){
        ScreenCommand command = new ScreenCommand(activity, R.id.edit_command,message);
        request(command);
    }
}
