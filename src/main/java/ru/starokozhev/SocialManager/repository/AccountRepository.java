package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.entity.OrderProduct;
import ru.starokozhev.SocialManager.entity.Product;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    List<Account> findAllByProduct(Product product);

    List<Account> findAllByOrderProduct(OrderProduct orderProduct);

    Account findAccountById(Long id);

}
