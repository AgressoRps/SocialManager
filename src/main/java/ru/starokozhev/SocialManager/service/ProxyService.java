package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.filter.ProxyAccountFilter;
import ru.starokozhev.SocialManager.dto.ProxyWrapper;
import ru.starokozhev.SocialManager.entity.Proxy;
import ru.starokozhev.SocialManager.repository.ProxyRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProxyService {

    private ProxyRepository proxyRepository;

    public ProxyWrapper add(ProxyWrapper wrapper) {
        Proxy proxyProduct = new Proxy();
        wrapper.fromWrapper(proxyProduct);

        //TODO find bought account if != null -> set to proxyAccount or throws exception

        //TODO set dateCreate
        //todo update dateLastUse

        return new ProxyWrapper(proxyRepository.save(proxyProduct));
    }

    public ProxyWrapper edit(ProxyWrapper wrapper) {
        Proxy proxyProduct = proxyRepository.findProxyById(wrapper.getId());

        //TODO add proxy id to message
        if (proxyProduct == null)
            throw new IllegalArgumentException("Прокси не найден");

        wrapper.fromWrapper(proxyProduct);

        //todo update dateLastUse

        return new ProxyWrapper(proxyRepository.save(proxyProduct));
    }

    public ProxyWrapper get(Long id) {
        Proxy proxyProduct = proxyRepository.findProxyById(id);

        //TODO add proxy id to message
        if (proxyProduct == null)
            throw new IllegalArgumentException("Прокси не найден");

        return new ProxyWrapper(proxyProduct);
    }

    public List<ProxyWrapper> list(ProxyAccountFilter filter) {
        //TODO build specification
        return null;
    }

    public void delete(Long id) {
        Proxy proxyProduct = proxyRepository.findProxyById(id);

        //TODO add proxy id to message
        if (proxyProduct == null)
            throw new IllegalArgumentException("Прокси не найден");

        proxyProduct.setDateClose(LocalDateTime.now());
        proxyRepository.save(proxyProduct);
    }

}
