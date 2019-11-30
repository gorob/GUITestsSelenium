package com.gorob.guitests.framework;

public abstract class AbstractSeleniumPageObject {
    private WebdriverUtil webdriverUtil;

    protected WebdriverUtil getWebdriverUtil() {
        return webdriverUtil;
    }

    public AbstractSeleniumPageObject(WebdriverUtil webdriverUtil){
        this.webdriverUtil = webdriverUtil;
    }
}
