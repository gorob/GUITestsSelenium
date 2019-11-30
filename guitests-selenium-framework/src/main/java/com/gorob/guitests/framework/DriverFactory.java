package com.gorob.guitests.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
    public static final String BROWSER_KEY = "BROWSER";
    public static final String BROWSER_VALUE_FIREFOX = "FIREFOX";
    public static final String BROWSER_VALUE_CHROME = "CHROME";
    public static final String BROWSER_VALUE_INTERNET_EXPLORER = "IE";

    public static final String BROWSER_LOGGING_KEY = "BROWSER_LOGGING";
    public static final String BROWSER_LOGGING_VALUE_ON = "true";
    public static final String BROWSER_LOGGING_VALUE_OFF = "false";

    public static WebDriver getDriver(){
        switch (getBrowserName()){
            case BROWSER_VALUE_FIREFOX:
                return createFirefoxWebDriver();

            case BROWSER_VALUE_CHROME:
                return createChromeWebDriver();

            case BROWSER_VALUE_INTERNET_EXPLORER:
                return createInternetExplorerWebDriver();

            default:
                return createFirefoxWebDriver();
        }
    }

    private static WebDriver createInternetExplorerWebDriver(){
        return new InternetExplorerDriver();
    }

    private static WebDriver createChromeWebDriver(){
        if (!isBrowserLoggingOn()){
            System.setProperty("webdriver.chrome.silentOutput", "true"); // kein Browser-Logging
        }
        return new ChromeDriver();
    }

    private static WebDriver createFirefoxWebDriver(){
        if (!isBrowserLoggingOn()) {
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null"); // kein Browser-Logging
        }
        return new FirefoxDriver();
    }

    private static String getBrowserName(){
        return getEnvOrPropertyValue(BROWSER_KEY, BROWSER_VALUE_FIREFOX);
    }

    private static boolean isBrowserLoggingOn(){
        return Boolean.getBoolean(getEnvOrPropertyValue(BROWSER_LOGGING_KEY, BROWSER_LOGGING_VALUE_OFF));
    }

    private static String getEnvOrPropertyValue(String key, String defaultValue){
        String value = System.getenv(key);
        if (value==null) {
            value = System.getProperty(key);
        }
        if (value==null) {
            value = defaultValue;
        }
        return value;
    }
}
