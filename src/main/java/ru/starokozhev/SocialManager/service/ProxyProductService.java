package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.filter.ProxyAccountFilter;
import ru.starokozhev.SocialManager.dto.ProxyAccountWrapper;
import ru.starokozhev.SocialManager.entity.ProxyProduct;
import ru.starokozhev.SocialManager.repository.ProxyProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProxyProductService {

    private ProxyProductRepository proxyProductRepository;

    public ProxyAccountWrapper add(ProxyAccountWrapper wrapper) {
        ProxyProduct proxyProduct = new ProxyProduct();
        wrapper.fromWrapper(proxyProduct);

        //TODO find bought account if != null -> set to proxyAccount or throws exception

        //TODO set dateCreate
        //todo update dateLastUse

        return new ProxyAccountWrapper(proxyProductRepository.save(proxyProduct));
    }

    public ProxyAccountWrapper edit(ProxyAccountWrapper wrapper) {
        ProxyProduct proxyProduct = proxyProductRepository.findProxyProductById(wrapper.getId());

        //TODO add proxy id to message
        if (proxyProduct == null)
            throw new IllegalArgumentException("Прокси не найден");

        wrapper.fromWrapper(proxyProduct);

        //todo update dateLastUse

        return new ProxyAccountWrapper(proxyProductRepository.save(proxyProduct));
    }

    public ProxyAccountWrapper get(Long id) {
        ProxyProduct proxyProduct = proxyProductRepository.findProxyProductById(id);

        //TODO add proxy id to message
        if (proxyProduct == null)
            throw new IllegalArgumentException("Прокси не найден");

        return new ProxyAccountWrapper(proxyProduct);
    }

    public List<ProxyAccountWrapper> list(ProxyAccountFilter filter) {
        //TODO build specification
        return null;
    }

    public void delete(Long id) {
        ProxyProduct proxyProduct = proxyProductRepository.findProxyProductById(id);

        //TODO add proxy id to message
        if (proxyProduct == null)
            throw new IllegalArgumentException("Прокси не найден");

        proxyProduct.setDateClose(LocalDateTime.now());
        proxyProductRepository.save(proxyProduct);
    }

}
