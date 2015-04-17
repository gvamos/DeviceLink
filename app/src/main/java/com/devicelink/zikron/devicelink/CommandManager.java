package com.devicelink.zikron.devicelink;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by gvamos on 4/13/15.
 */
public class CommandManager implements Runnable {

    BlockingQueue<ManagedCommand> commands = new LinkedBlockingQueue<ManagedCommand>();
    public void request(ManagedCommand command){ commands.add(command); }

    Thread managerThread;

    public Thread init(){
        managerThread = new Thread(this);
        managerThread.start();
        return managerThread;
    }

    public void execute() throws InterruptedException {
        ManagedCommand cmd = commands.take();
        cmd.exec();
    }

    public int queueLength(){ return commands.size(); }

    @Override
    public void run() {

        while(true){
            try {
                execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
