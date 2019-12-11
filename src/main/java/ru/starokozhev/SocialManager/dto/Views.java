package ru.starokozhev.SocialManager.dto;

public final class Views {

    public interface OnlyId {}

    public interface AccessList extends OnlyId {}

    public interface AccessCard extends AccessList {}

    public interface AccessForRegister extends AccessCard {}

    public interface AccessForAdmin extends AccessForRegister {}

}
