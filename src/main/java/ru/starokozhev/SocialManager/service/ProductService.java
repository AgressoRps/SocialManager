package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.filter.AccountFilter;
import ru.starokozhev.SocialManager.dto.ProductWrapper;
import ru.starokozhev.SocialManager.entity.Product;
import ru.starokozhev.SocialManager.repository.AccountRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final AccountRepository accountRepository;

    @Transactional
    public ProductWrapper add(ProductWrapper wrapper) {
        Product product = new Product();
        wrapper.fromWrapper(product);

        return new ProductWrapper(accountRepository.save(product));
    }

    @Transactional
    public ProductWrapper edit(ProductWrapper wrapper) {
        Product product = accountRepository.findAccountById(wrapper.getId());

        //TODO выводить id аккаунта, который ищем
        if (product == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        wrapper.fromWrapper(product);

        return new ProductWrapper(accountRepository.save(product));
    }

    public ProductWrapper get(Long id) {
        Product product = accountRepository.findAccountById(id);

        //TODO выводить id аккаунта, который ищем
        if (product == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        return new ProductWrapper(product);
    }

    public List<ProductWrapper> list(AccountFilter filter) {
        //TODO specifications
        return null;
    }

    public void delete(Long id) {
        Product product = accountRepository.findAccountById(id);

        //TODO выводить id аккаунта, который ищем
        if (product == null)
            throw new IllegalArgumentException("Аккаунт не найден");

        product.setDateClose(LocalDateTime.now());
        accountRepository.save(product);
    }

}
