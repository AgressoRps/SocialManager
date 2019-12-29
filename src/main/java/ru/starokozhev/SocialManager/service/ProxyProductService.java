package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.filter.ProxyAccountFilter;
import ru.starokozhev.SocialManager.dto.ProxyProductWrapper;
import ru.starokozhev.SocialManager.entity.ProxyProduct;
import ru.starokozhev.SocialManager.repository.ProxyProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProxyProductService {

    private ProxyProductRepository proxyProductRepository;

    public ProxyProductWrapper add(ProxyProductWrapper wrapper) {
        ProxyProduct proxyProduct = new ProxyProduct();
        wrapper.fromWrapper(proxyProduct);

        //TODO find bought account if != null -> set to proxyAccount or throws exception

        //TODO set dateCreate
        //todo update dateLastUse

        return new ProxyProductWrapper(proxyProductRepository.save(proxyProduct));
    }

    public ProxyProductWrapper edit(ProxyProductWrapper wrapper) {
        ProxyProduct proxyProduct = proxyProductRepository.findProxyProductById(wrapper.getId());

        //TODO add proxy id to message
        if (proxyProduct == null)
            throw new IllegalArgumentException("Прокси не найден");

        wrapper.fromWrapper(proxyProduct);

        //todo update dateLastUse

        return new ProxyProductWrapper(proxyProductRepository.save(proxyProduct));
    }

    public ProxyProductWrapper get(Long id) {
        ProxyProduct proxyProduct = proxyProductRepository.findProxyProductById(id);

        //TODO add proxy id to message
        if (proxyProduct == null)
            throw new IllegalArgumentException("Прокси не найден");

        return new ProxyProductWrapper(proxyProduct);
    }

    public List<ProxyProductWrapper> list(ProxyAccountFilter filter) {
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
