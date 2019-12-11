package ru.starokozhev.SocialManager.enums;

public enum AccountType {
    SOCIAL_NETWORK("Социальная сеть"),
    EMAIL("Электронная почта");

    private String friendlyName;

    AccountType(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
