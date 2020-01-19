package ru.starokozhev.SocialManager.enums.vtope;

public enum VtopeProxyMode {
    auto("Автоматический"),
    manual("Ручной");

    private String friendlyName;

    VtopeProxyMode(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
