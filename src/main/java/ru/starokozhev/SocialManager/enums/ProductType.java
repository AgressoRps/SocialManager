package ru.starokozhev.SocialManager.enums;

public enum ProductType {
    SOCIAL_NETWORK("Социальная сеть"),
    EMAIL("Электронная почта");

    private String friendlyName;

    ProductType(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
