package ru.starokozhev.SocialManager.enums;

public enum AccountState {
    USE_IN_VTOPE("Используется Vtope"),
    NOT_USE("Нигде не используется"),
    FROM_REGISTERED("После регистрации"),
    BLOCKED_IN_VTOPE("Заблокирован Vtope");

    private String friendlyName;

    AccountState(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
