package ru.starokozhev.SocialManager.enums;

public enum ProxyType {
    http("http"),
    https("https"),
    socks5("socks5");

    private String friendlyName;

    ProxyType(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
