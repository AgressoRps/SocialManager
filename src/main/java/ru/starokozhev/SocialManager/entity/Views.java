package ru.starokozhev.SocialManager.entity;

public final class Views {

    public interface OnlyId {}

    public interface AccessList extends OnlyId {}

    public interface AccessCard extends AccessList {}

    public interface AccessForAdmin extends AccessCard {}

}
