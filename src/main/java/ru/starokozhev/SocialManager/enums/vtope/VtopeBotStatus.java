package ru.starokozhev.SocialManager.enums.vtope;

public enum VtopeBotStatus {
    success("Бот работает, все хорошо"),
    warning("Бот работает, но есть проблемы"),
    danger("Бот не зарабатывает поинты");

    private String friendlyName;

    VtopeBotStatus(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
