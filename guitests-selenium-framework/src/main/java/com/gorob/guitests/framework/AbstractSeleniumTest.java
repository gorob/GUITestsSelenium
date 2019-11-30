package com.gorob.guitests.framework;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

public abstract class AbstractSeleniumTest {
    private WebdriverUtil webdriverUtil;

    @Rule
    public TestName testName = new TestName();

    public WebdriverUtil getWebdriverUtil() {
        return webdriverUtil!=null ? webdriverUtil: initWebdriverUtil();
    }

    private WebdriverUtil initWebdriverUtil(){
        webdriverUtil = new WebdriverUtil();
        return webdriverUtil;
    }

    protected TestName getTestName() {
        return testName;
    }

    @AfterClass
    public static void closeDriver(){
        Driver.closeDriver();
    }

    @Before
    public void setUp(){
        Driver.initializeDriver();

        getWebdriverUtil().setServerPort(getServerPort());
        getWebdriverUtil().setTestName(getTestName().getMethodName());
        getWebdriverUtil().initWindow();
    }

    protected abstract int getServerPort();
}
