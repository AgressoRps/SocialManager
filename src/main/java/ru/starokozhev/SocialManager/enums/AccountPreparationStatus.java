package ru.starokozhev.SocialManager.enums;

public enum AccountPreparationStatus {
    SUCCESS("Успешно завершено"),
    ERROR("Ошибка");

    private String friendlyName;

    AccountPreparationStatus(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
