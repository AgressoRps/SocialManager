package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.starokozhev.SocialManager.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findUserByEmail(String email);

    User findUserByLogin(String login);

    User findUserByEmailOrLogin(String email, String login);

    User findUserById(Long id);

    List<User> findAllByDateCloseIsNullOrderByIdDesc();

}
