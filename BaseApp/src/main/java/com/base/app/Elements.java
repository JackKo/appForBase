package com.base.app;

import org.openqa.selenium.By;

/**
 * Created by JK on 18.06.15.
 */
public enum  Elements {
    SAVE_NEW_LEAD_ELEMENT(By.xpath("//button[text()='Save']")),
    NEW_LEAD_FN(By.id("lead-first-name")),
    NEW_LEAD_SN(By.id("lead-last-name")),
    LEAD_STATUS(By.xpath("//div[@class='status']" +
            "/div[@class='dropdown']//span[@class='lead-status']")),
    SETTINGS_LEAD_STATUS(By.xpath("//a[@data-toggle='lead-status']")),
    SETTINGS_ADD_LEAD_STATUS(By.xpath("//button[text()=' Add Lead Status']")),
    SETTINGS_ADD_LEAD_STATUS_SAVE_BUTTON(By.cssSelector("#lead-status > " +
            "div:nth-child(3) > " +
            "div:nth-child(2) > " +
            "form:nth-child(1) > " +
            "fieldset:nth-child(1) > " +
            "div:nth-child(3) > " +
            "div:nth-child(1) > " +
            "button:nth-child(1)")),
    SETTINGS_ADD_LEAD_STATUS_INPUT(By.cssSelector("#lead-status > " +
            "div:nth-child(3) > " +
            "div:nth-child(2) > " +
            "form:nth-child(1) > " +
            "fieldset:nth-child(1) > " +
            "div:nth-child(2) > " +
            "div:nth-child(2) > " +
            "input:nth-child(1)")),
    LEAD_LIST(By.id("nav-leads"));


    Elements(By locator) {
        this.locator = locator;
    }

    private final By locator;

    public By getLocator() {
        return locator;
    }
}
