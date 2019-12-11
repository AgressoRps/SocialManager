package ru.starokozhev.SocialManager.enums;

public enum ProxyHttpType {
    HTTP("HTTP"),
    HTTPS("HTTPS");

    private String friendlyName;

    ProxyHttpType(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
