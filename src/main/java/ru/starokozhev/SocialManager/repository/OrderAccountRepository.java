package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.starokozhev.SocialManager.entity.OrderProduct;
import ru.starokozhev.SocialManager.entity.User;

import java.util.List;

public interface OrderAccountRepository extends JpaRepository<OrderProduct, Long>,
        JpaSpecificationExecutor<OrderProduct> {

    List<OrderProduct> findAllByUser(User user);

    List<OrderProduct> findAllByIsPayed(Boolean isPayed);

    OrderProduct findOrderAccountById(Long id);

}
