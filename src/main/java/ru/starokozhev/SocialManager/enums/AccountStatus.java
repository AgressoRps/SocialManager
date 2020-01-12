package ru.starokozhev.SocialManager.enums;

public enum AccountStatus {
    manual("Ручная авторизация"),
    ipv6proxy("Соцсеть не поддерживает IPv6"),
    loaderrormobilecode("Специальная авторизация"),
    duplicate("Дубликат аккаунта"),
    banned("Заблокирован"),
    badauth("Неверные логин/пароль"),
    badproxy("Привязанный прокси не работает"),
    level_0("Аккаунт 0 уровня"),
    nonvalidating("Ожидает проверки"),
    validating("Автоматическая проверка"),
    tomanual("Автоматическая проверка"),
    tomanualmobilecode("Автоматическая проверка"),
    prevalidating("Автоматическая проверка"),
    badpreauth("Автоматическая проверка"),
    okpreauth("Автоматическая проверка"),
    waitproxy("Аккаунт ожидает прокси"),
    queued("В очереди на работу"),
    nonworking("Ожидает работы"),
    working("В работе"),
    toinfo("В работе"),
    sleepingtimestamp("Аккаунт отдыхает до timestamp"),
    workingblocked("Блок на действия со стороны соцсети");

    private String friendlyName;

    AccountStatus(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
