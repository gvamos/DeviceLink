package com.devicelink.zikron.devicelink;

/**
 * Created by gvamos on 4/13/15.
 */
public class ManagedCommand {

    private String status = "created";

    public void exec(){
        if (status != "created"){
            throw new IllegalStateException("Command cannot be re-executed");
        }
        status = "started";
    };
    public String status(){return status ;};

}
