package ru.starokozhev.SocialManager.enums.vtope;

public enum VtopeBotAccess {
    on("Бот доступен"),
    off("Бот выключен"),
    no_connection("Нет связи с ботом");

    private String friendlyName;

    VtopeBotAccess(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
