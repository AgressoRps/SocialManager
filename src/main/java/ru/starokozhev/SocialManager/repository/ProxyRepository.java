package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.starokozhev.SocialManager.entity.Proxy;
import ru.starokozhev.SocialManager.enums.ProxyHttpType;
import ru.starokozhev.SocialManager.enums.ProxyState;

import java.util.List;

public interface ProxyRepository extends JpaRepository<Proxy, Long>,
        JpaSpecificationExecutor<Proxy> {

    List<Proxy> findAllByProxyState(ProxyState proxyState);

    List<Proxy> findAllByProxyHttpType(ProxyHttpType proxyHttpType);

    Proxy findProxyById(Long id);

}
