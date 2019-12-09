package ru.starokozhev.SocialManager.entity;

public final class Views {

    public interface OnlyId {}

    public interface AccessList extends OnlyId {}

    public interface AccessCard extends AccessList {}

    public interface AccessForRegister extends AccessCard {}

    public interface AccessForAdmin extends AccessForRegister {}

}
