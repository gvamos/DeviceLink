package com.devicelink.zikron.devicelink;

import android.test.InstrumentationTestCase;

import junit.framework.TestCase;

/**
 * Created by gvamos on 4/13/15.
 */
public class CommandManagerTest extends TestCase {

    public void testCommand() throws InterruptedException {

        CommandManager mgr = new CommandManager();
        ManagedCommand cmd = new ManagedCommand();


        Integer ql0 = mgr.queueLength();
        mgr.request(cmd);
        Integer ql1 = mgr.queueLength();
        mgr.execute();
        Integer ql2 = mgr.queueLength();
        assertEquals(new Integer(0),ql0);
        assertEquals(new Integer(1),ql1);
        assertEquals(new Integer(0),ql2);
    }
}