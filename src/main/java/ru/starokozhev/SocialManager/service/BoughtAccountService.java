package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.BoughtAccountFilter;
import ru.starokozhev.SocialManager.dto.BoughtAccountWrapper;
import ru.starokozhev.SocialManager.entity.BoughtAccount;
import ru.starokozhev.SocialManager.repository.BoughtAccountRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoughtAccountService {

    private BoughtAccountRepository boughtAccountRepository;

    //TODO provide order to final, use after transactions for accounts (maybe)

    public BoughtAccountWrapper add(BoughtAccountWrapper wrapper) {
        BoughtAccount boughtAccount = new BoughtAccount();
        wrapper.fromWrapper(boughtAccount);

        //TODO accounts
        //TODO setCurrentUser
        //TODO setDateOrder
        //TODO setDatePayed когда опалтит
        //TODO setIsPayed когда оплатит

        return new BoughtAccountWrapper(boughtAccountRepository.save(boughtAccount));
    }

    public BoughtAccountWrapper edit(BoughtAccountWrapper wrapper) {
        BoughtAccount boughtAccount = boughtAccountRepository.findBoughtAccountById(wrapper.getId());

        //TODO add id order to message
        if (boughtAccount == null)
            throw new IllegalArgumentException("Заказ не найден");

        wrapper.fromWrapper(boughtAccount);

        return new BoughtAccountWrapper(boughtAccountRepository.save(boughtAccount));
    }

    public BoughtAccountWrapper get(Long id) {
        BoughtAccount boughtAccount = boughtAccountRepository.findBoughtAccountById(id);

        //TODO add id order to message
        if (boughtAccount == null)
            throw new IllegalArgumentException("Заказ не найден");

        return new BoughtAccountWrapper(boughtAccount);
    }

    public List<BoughtAccountWrapper> list(BoughtAccountFilter filter) {
        //TODO build specification
        return null;
    }

    //TODO add close method (delete)

}
