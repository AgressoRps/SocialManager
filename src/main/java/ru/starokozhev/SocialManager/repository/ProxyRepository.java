package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.starokozhev.SocialManager.entity.Bot;
import ru.starokozhev.SocialManager.entity.Proxy;
import ru.starokozhev.SocialManager.entity.User;

import java.util.List;

public interface ProxyRepository extends JpaRepository<Proxy, Long> {

    Proxy findProxyById(Long id);

    List<Proxy> findAllByUser(User user);

    List<Proxy> findAllByBot(Bot bot);

    Proxy findProxyByIpAndPortAndUser(String ip, String port, User user);

    Proxy findByIpAndUser(String ip, User user);

}
