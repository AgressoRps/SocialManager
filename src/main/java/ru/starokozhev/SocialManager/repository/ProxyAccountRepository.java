package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.starokozhev.SocialManager.entity.OrderAccount;
import ru.starokozhev.SocialManager.entity.ProxyAccount;
import ru.starokozhev.SocialManager.enums.ProxyHttpType;
import ru.starokozhev.SocialManager.enums.ProxyState;

import java.util.List;

public interface ProxyAccountRepository extends JpaRepository<ProxyAccount, Long>,
        JpaSpecificationExecutor<ProxyAccount> {

    List<ProxyAccount> findAllByOrderAccount(OrderAccount orderAccount);

    List<ProxyAccount> findAllByProxyState(ProxyState proxyState);

    List<ProxyAccount> findAllByProxyHttpType(ProxyHttpType proxyHttpType);

    ProxyAccount findProxyAccountById(Long id);

}
