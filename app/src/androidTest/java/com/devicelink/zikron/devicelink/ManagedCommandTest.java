package com.devicelink.zikron.devicelink;

import android.test.InstrumentationTestCase;

import junit.framework.TestCase;

/**
 * Created by gvamos on 4/13/15.
 */
public class ManagedCommandTest extends TestCase {

    public void testCommand(){

        ManagedCommand cmd = new ManagedCommand();
        cmd.exec();
        String status = cmd.status();
        assertEquals("started", status);
    }
}
