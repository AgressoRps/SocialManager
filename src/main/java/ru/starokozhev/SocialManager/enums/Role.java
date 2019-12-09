package ru.starokozhev.SocialManager.enums;

public enum Role {
    ADMIN("Администратор"),
    USER("Пользователь");

    private String friendlyName;

    Role(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
