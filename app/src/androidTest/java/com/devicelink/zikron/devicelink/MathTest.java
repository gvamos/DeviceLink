package com.devicelink.zikron.devicelink;

import android.test.InstrumentationTestCase;

/**
 * Created by gvamos on 4/11/15.
 */
public class MathTest extends InstrumentationTestCase{
    public void testMath(){
        Integer first = 2;
        Integer second = 3;
        Integer third = first + second;
        assertEquals(third, new Integer(5));
    }
}
