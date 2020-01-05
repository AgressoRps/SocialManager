package ru.starokozhev.SocialManager.enums;

public enum ProxyHttpType {
    HTTP("HTTP"),
    SOCKS_5("SOCKS 5");

    private String friendlyName;

    ProxyHttpType(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
