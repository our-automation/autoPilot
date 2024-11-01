package com.automation.mobile;

import com.automation.mobileApp.AndroidDriverManagerUtil;
import com.automation.utils.listeners.RunWithTestNG;
import com.automation.webutils.LocatorType;
import io.appium.java_client.android.AndroidDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class MobileAutomation extends RunWithTestNG {
    @Autowired
    private AndroidDriverManagerUtil androidDriverManagerUtil;
//    @Test
    public void launchChromeBrowser(){
        AndroidDriver androidDriver = androidDriverManagerUtil.getAndroidDriver();
        androidDriver.get("https://www.google.com/");
        androidDriverManagerUtil.sendkeys(LocatorType.XPATH,"//textarea[@title='Search']","my search");
        androidDriverManagerUtil.doKeyBoardEnter();
    }

}
