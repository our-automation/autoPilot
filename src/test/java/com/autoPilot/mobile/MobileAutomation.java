package com.autoPilot.mobile;

import com.autoPilot.mobileApp.AndroidDriverManagerUtil;
import com.autoPilot.utils.listeners.RunWithTestNG;
import com.autoPilot.webutils.LocatorType;
import io.appium.java_client.android.AndroidDriver;
import org.springframework.beans.factory.annotation.Autowired;

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
