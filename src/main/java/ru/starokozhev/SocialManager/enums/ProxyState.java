package ru.starokozhev.SocialManager.enums;

public enum ProxyState {
    USE("Используется"),
    NOT_USE("Не используется"),
    ACCOUNT_BLOCKED("Аккаунт заблокирован"),
    CLOSED("Отключен"),
    NEW("Новый");

    private String friendlyName;

    ProxyState(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
