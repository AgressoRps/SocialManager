package ru.starokozhev.SocialManager.enums;

public enum BotStatus {
    success("Бот работает, все хорошо"),
    warning("Бот работает, но есть проблемы"),
    danger("Бот не зарабатывает поинты");

    private String friendlyName;

    BotStatus(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
