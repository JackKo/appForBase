package com.base.app.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by JK on 18.06.15.
 */
public class AreLeadStatusEqualToMatcher extends BaseMatcher<List<WebElement>> {

    private String desiredValue;

    public AreLeadStatusEqualToMatcher(String desiredValue) {
        this.desiredValue = desiredValue;
    }

    @Factory
    public static AreLeadStatusEqualToMatcher allLeadsWithNameHasCorrectValue(String value){
        return new AreLeadStatusEqualToMatcher(value);
    }

    @Override
    public boolean matches(Object o) {
        return false;
    }

    @Override
    public void describeTo(Description description) {

    }
}
