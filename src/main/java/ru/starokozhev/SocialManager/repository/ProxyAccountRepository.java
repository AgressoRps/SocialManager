package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.starokozhev.SocialManager.entity.OrderProduct;
import ru.starokozhev.SocialManager.entity.ProxyProduct;
import ru.starokozhev.SocialManager.enums.ProxyHttpType;
import ru.starokozhev.SocialManager.enums.ProxyState;

import java.util.List;

public interface ProxyAccountRepository extends JpaRepository<ProxyProduct, Long>,
        JpaSpecificationExecutor<ProxyProduct> {

    List<ProxyProduct> findAllByOrderAccount(OrderProduct orderProduct);

    List<ProxyProduct> findAllByProxyState(ProxyState proxyState);

    List<ProxyProduct> findAllByProxyHttpType(ProxyHttpType proxyHttpType);

    ProxyProduct findProxyAccountById(Long id);

}
