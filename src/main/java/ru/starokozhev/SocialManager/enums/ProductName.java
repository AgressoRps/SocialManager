package ru.starokozhev.SocialManager.enums;

public enum ProductName {
    RAMBLER("Рамблер"),
    INSTAGRAM("Инстаграм");

    private String friendlyName;

    ProductName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
