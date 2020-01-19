package ru.starokozhev.SocialManager.enums.vtope;

public enum VtopeAccountService {
    v("ВКонтакте"),
    t("Twitter"),
    i("Instagram"),
    a("Ask.fm"),
    y("Youtube"),
    o("Ok.ru"),
    m("Telegram");

    private String friendlyName;

    VtopeAccountService(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

}
