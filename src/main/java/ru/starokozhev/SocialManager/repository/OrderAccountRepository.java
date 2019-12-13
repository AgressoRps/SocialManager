package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.starokozhev.SocialManager.entity.OrderAccount;
import ru.starokozhev.SocialManager.entity.User;

import java.util.List;

public interface OrderAccountRepository extends JpaRepository<OrderAccount, Long>,
        JpaSpecificationExecutor<OrderAccount> {

    List<OrderAccount> findAllByUser(User user);

    List<OrderAccount> findAllByIsPayed(Boolean isPayed);

    OrderAccount findBoughtAccountById(Long id);

}
