package com.base.app;

/**
 * Created by JK on 17.06.15.
 */
public enum Page {
    LEADS("https://app.futuresimple.com/leads"),
    ADD_NEW_LEAD("https://app.futuresimple.com/leads/new"),
    STATUS_SETTINGS("https://app.futuresimple.com/settings/leads/lead-status");

    private final String uri;

    private Page(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
