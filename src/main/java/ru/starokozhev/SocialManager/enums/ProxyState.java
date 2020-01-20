package ru.starokozhev.SocialManager.enums;

public enum ProxyState {
    USE("Используется Vtope"),
    NOT_USE("Нигде не используется");

    private String friendlyName;

    ProxyState(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
