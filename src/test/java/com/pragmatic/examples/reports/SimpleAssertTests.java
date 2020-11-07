package com.pragmatic.examples.reports;


import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ExtentITestListenerClassAdapter.class})
public class SimpleAssertTests {


    @Test
    public void passTest() {
        Assert.assertTrue(true);
    }

    @Test
    public void failTest() {
        Assert.assertTrue(false);
    }

}