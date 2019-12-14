package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.filter.ProxyAccountFilter;
import ru.starokozhev.SocialManager.dto.ProxyAccountWrapper;
import ru.starokozhev.SocialManager.entity.ProxyAccount;
import ru.starokozhev.SocialManager.repository.ProxyAccountRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProxyAccountService {

    private ProxyAccountRepository proxyAccountRepository;

    public ProxyAccountWrapper add(ProxyAccountWrapper wrapper) {
        ProxyAccount proxyAccount = new ProxyAccount();
        wrapper.fromWrapper(proxyAccount);

        //TODO find bought account if != null -> set to proxyAccount or throws exception

        //TODO set dateCreate
        //todo update dateLastUse

        return new ProxyAccountWrapper(proxyAccountRepository.save(proxyAccount));
    }

    public ProxyAccountWrapper edit(ProxyAccountWrapper wrapper) {
        ProxyAccount proxyAccount = proxyAccountRepository.findProxyAccountById(wrapper.getId());

        //TODO add proxy id to message
        if (proxyAccount == null)
            throw new IllegalArgumentException("Прокси не найден");

        wrapper.fromWrapper(proxyAccount);

        //todo update dateLastUse

        return new ProxyAccountWrapper(proxyAccountRepository.save(proxyAccount));
    }

    public ProxyAccountWrapper get(Long id) {
        ProxyAccount proxyAccount = proxyAccountRepository.findProxyAccountById(id);

        //TODO add proxy id to message
        if (proxyAccount == null)
            throw new IllegalArgumentException("Прокси не найден");

        return new ProxyAccountWrapper(proxyAccount);
    }

    public List<ProxyAccountWrapper> list(ProxyAccountFilter filter) {
        //TODO build specification
        return null;
    }

    public void delete(Long id) {
        ProxyAccount proxyAccount = proxyAccountRepository.findProxyAccountById(id);

        //TODO add proxy id to message
        if (proxyAccount == null)
            throw new IllegalArgumentException("Прокси не найден");

        proxyAccount.setDateClose(LocalDateTime.now());
        proxyAccountRepository.save(proxyAccount);
    }

}
