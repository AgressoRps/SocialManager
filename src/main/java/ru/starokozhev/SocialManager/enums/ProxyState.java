package ru.starokozhev.SocialManager.enums;

public enum ProxyState {
    USED("Использованный"),
    NEW("Новый");

    private String friendlyName;

    ProxyState(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
