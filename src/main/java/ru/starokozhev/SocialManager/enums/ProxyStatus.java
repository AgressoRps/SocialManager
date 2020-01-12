package ru.starokozhev.SocialManager.enums;

public enum ProxyStatus {
    tooslow("Прокси слишком медленный"),
    badstate("Прокси не работает"),
    queued_proxy("Прокси ждет, пока нужный аккаунт начнет работу"),
    tovalidate("Автоматическая проверка"),
    validating("Автоматическая проверка"),
    working("Прокси в работе");

    private String friendlyName;

    ProxyStatus(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
