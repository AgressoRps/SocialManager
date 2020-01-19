package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.starokozhev.SocialManager.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findUserByEmailIgnoreCase(String email);

    User findUserByLoginIgnoreCase(String login);

    User findUserByEmailIgnoreCaseOrLoginIgnoreCase(String email, String login);

    User findUserById(Long id);

    List<User> findUsersByNotifyChangesInBotIsNotNull();

    List<User> findUsersByUpdateFromVtopeIsNotNull();

    List<User> findAllByDateCloseIsNullOrderByIdDesc();
}
