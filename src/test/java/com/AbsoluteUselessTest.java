package com;

import org.junit.Assert;
import org.junit.Test;

public class AbsoluteUselessTest {
    @Test
    public void test()
    {
        int x = 2;
        int y = 23;
        Assert.assertEquals(46, x*y);
    }
}
