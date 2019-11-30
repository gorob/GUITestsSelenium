package com.gorob.guitests.framework;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DriverFactoryTest {
    @Test
    public void testGetEnvOrPropertyValue(){
        assertEquals("defaultValue", DriverFactory.getEnvOrPropertyValue("someKey", "defaultValue"));

        System.setProperty("someKey", "someValue");
        assertEquals("someValue", DriverFactory.getEnvOrPropertyValue("someKey", "defaultValue"));

        System.clearProperty("someKey");

        assertEquals("defaultValue", DriverFactory.getEnvOrPropertyValue("someKey", "defaultValue"));
    }
}
