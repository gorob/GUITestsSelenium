package com.gorob.guitests.framework;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public abstract class AbstractSeleniumScreenshotTaker extends TestWatcher {
    private WebDriver webDriver;
    private File targetDirectory4Screenshots;

    public AbstractSeleniumScreenshotTaker(WebDriver webDriver, File targetDirectory4Screenshots){
        this.webDriver = webDriver;
        this.targetDirectory4Screenshots = targetDirectory4Screenshots;
    }

    private WebDriver getWebDriver() {
        return webDriver;
    }

    private File getTargetDirectory4Screenshots() {
        return targetDirectory4Screenshots;
    }

    protected void takeScreenshot(Description description){
        takeScreenshot(description, getTargetDirectory4Screenshots());
    }

    protected void takeScreenshot(Description description, File targetDirectory4Screenshots){
        File screenShotAs = ((TakesScreenshot)getWebDriver()).getScreenshotAs(OutputType.FILE);
        copyScreenshotToTargetFile(description, screenShotAs, targetDirectory4Screenshots);
    }

    private void copyScreenshotToTargetFile(Description description, File screenshotAs, File targetDirectory4Screenshots){
        createDirectoryIfNotExists(targetDirectory4Screenshots);
        try {
            FileUtils.copyFile(screenshotAs, getFile4Screenshot(description, targetDirectory4Screenshots));
        } catch (IOException ex) {
            throw new RuntimeException("Error on taking screenshot!", ex);
        }
    }

    private void createDirectoryIfNotExists(File targetDirectory4Screenshots){
        if (!getTargetDirectory4Screenshots().isDirectory()){
            if (!getTargetDirectory4Screenshots().mkdirs()){
                throw new RuntimeException("Error while creating target directory for screenshots: " + getTargetDirectory4Screenshots().getAbsolutePath());
            }
        }
    }

    private File getFile4Screenshot(Description description, File targetDirectory){
        return new File(targetDirectory, getFilename4Screenshot(description));
    }

    private String getFilename4Screenshot(Description description){
        String testClassName = description.getTestClass().getSimpleName();
        String testMethodName = description.getMethodName();
        return testClassName + "-" + testMethodName + ".png";
    }
}
