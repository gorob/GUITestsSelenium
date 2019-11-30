package com.gorob.guitests.framework;

import org.openqa.selenium.WebDriver;

public class Driver {
    static WebDriver webDriver;

    static void initializeDriver(){
        if (isWebDriverInitialized()){
            return;
        }
        webDriver = DriverFactory.getDriver();
    }

    static void closeDriver(){
        webDriver.quit();
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static boolean isWebDriverInitialized(){
        return getWebDriver()!=null;
    }
}
