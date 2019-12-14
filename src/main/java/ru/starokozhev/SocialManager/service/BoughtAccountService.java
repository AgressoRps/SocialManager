package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.filter.OrderAccountFilter;
import ru.starokozhev.SocialManager.dto.OrderAccountWrapper;
import ru.starokozhev.SocialManager.entity.OrderAccount;
import ru.starokozhev.SocialManager.repository.OrderAccountRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoughtAccountService {

    private OrderAccountRepository orderAccountRepository;

    //TODO provide order to final, use after transactions for accounts (maybe)

    public OrderAccountWrapper add(OrderAccountWrapper wrapper) {
        OrderAccount orderAccount = new OrderAccount();
        wrapper.fromWrapper(orderAccount);

        //TODO accounts
        //TODO setCurrentUser
        //TODO setDateOrder
        //TODO setDatePayed когда опалтит
        //TODO setIsPayed когда оплатит

        return new OrderAccountWrapper(orderAccountRepository.save(orderAccount));
    }

    public OrderAccountWrapper edit(OrderAccountWrapper wrapper) {
        OrderAccount orderAccount = orderAccountRepository.findBoughtAccountById(wrapper.getId());

        //TODO add id order to message
        if (orderAccount == null)
            throw new IllegalArgumentException("Заказ не найден");

        wrapper.fromWrapper(orderAccount);

        return new OrderAccountWrapper(orderAccountRepository.save(orderAccount));
    }

    public OrderAccountWrapper get(Long id) {
        OrderAccount orderAccount = orderAccountRepository.findBoughtAccountById(id);

        //TODO add id order to message
        if (orderAccount == null)
            throw new IllegalArgumentException("Заказ не найден");

        return new OrderAccountWrapper(orderAccount);
    }

    public List<OrderAccountWrapper> list(OrderAccountFilter filter) {
        //TODO build specification
        return null;
    }

    public void delete(Long id) {
        OrderAccount orderAccount = orderAccountRepository.findBoughtAccountById(id);

        //TODO add id order to message
        if (orderAccount == null)
            throw new IllegalArgumentException("Заказ не найден");

        orderAccount.setDateClose(LocalDateTime.now());
        orderAccountRepository.save(orderAccount);
    }

}
