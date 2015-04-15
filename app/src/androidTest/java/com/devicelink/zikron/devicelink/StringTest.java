package com.devicelink.zikron.devicelink;

import android.test.InstrumentationTestCase;

/**
 * Created by gvamos on 4/11/15.
 */
public class StringTest extends InstrumentationTestCase{
    public void testMath(){
        String first = "foo";
        String second = "bar";
        String third = first + second;
        assertEquals(third,"foobar");
    }
}
