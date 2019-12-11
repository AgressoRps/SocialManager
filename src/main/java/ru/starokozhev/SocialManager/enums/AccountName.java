package ru.starokozhev.SocialManager.enums;

public enum AccountName {
    RAMBLER("Рамблер"),
    INSTAGRAM("Инстаграм");

    private String friendlyName;

    AccountName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
