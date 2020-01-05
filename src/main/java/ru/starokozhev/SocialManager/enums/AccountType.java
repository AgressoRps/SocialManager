package ru.starokozhev.SocialManager.enums;

public enum AccountType {
    AUTO_REGISTERED("Автоматически зарегистрирован"),
    USER_ADDED("Добавлен пользователем");

    private String friendlyName;

    AccountType(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
