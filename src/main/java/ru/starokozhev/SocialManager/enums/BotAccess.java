package ru.starokozhev.SocialManager.enums;

public enum BotAccess {
    on("Доступен"),
    of("Выключен"),
    no_connection("Нет связи");

    private String friendlyName;

    BotAccess(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
