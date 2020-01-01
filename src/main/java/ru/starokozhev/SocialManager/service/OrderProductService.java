package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.OrderWrapper;
import ru.starokozhev.SocialManager.dto.TemporaryMailWrapper;
import ru.starokozhev.SocialManager.dto.filter.OrderAccountFilter;
import ru.starokozhev.SocialManager.dto.OrderProductWrapper;
import ru.starokozhev.SocialManager.entity.Account;
import ru.starokozhev.SocialManager.entity.OrderProduct;
import ru.starokozhev.SocialManager.entity.Product;
import ru.starokozhev.SocialManager.entity.ProxyProduct;
import ru.starokozhev.SocialManager.repository.OrderProductRepository;
import ru.starokozhev.SocialManager.repository.ProductRepository;
import ru.starokozhev.SocialManager.service.mail.TemporaryMailService;
import ru.starokozhev.SocialManager.service.registrator.InstagramService;
import ru.starokozhev.SocialManager.service.selenium.SeleniumService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final InstagramService instagramService;
    private final TemporaryMailService temporaryMailService;
    private final SeleniumService seleniumService;
    private final UserService userService;

    //TODO provide order to final, use after transactions for accounts (maybe)

    public OrderProductWrapper add(OrderProductWrapper wrapper) {
        OrderProduct orderProduct = new OrderProduct();
        wrapper.fromWrapper(orderProduct);

        //TODO accounts
        //TODO setCurrentUser
        //TODO setDateOrder
        //TODO setDatePayed когда опалтит
        //TODO setIsPayed когда оплатит

        return new OrderProductWrapper(orderProductRepository.save(orderProduct));
    }

    public OrderProductWrapper edit(OrderProductWrapper wrapper) {
        OrderProduct orderProduct = orderProductRepository.findOrderProductById(wrapper.getId());

        //TODO add id order to message
        if (orderProduct == null)
            throw new IllegalArgumentException("Заказ не найден");

        wrapper.fromWrapper(orderProduct);

        return new OrderProductWrapper(orderProductRepository.save(orderProduct));
    }

    public OrderProductWrapper register(OrderWrapper wrapper) {
        OrderProduct orderProduct = new OrderProduct();
        //List<Account> registeredAccounts = new ArrayList<>();
        //List<ProxyProduct> usedProxies = new ArrayList<>();

        for (int i = 0; i < wrapper.getCount(); i++) {
            try {
                ProxyProduct proxyProduct = null; //todo get proxy
                SeleniumService.createAndStartService();
                WebDriver driver = seleniumService.createDriver(proxyProduct);

                TemporaryMailWrapper temporaryMail = temporaryMailService.getTemporaryMail(driver);
                Account account = instagramService.registerAccount(temporaryMail, driver);
                boolean isActivated = temporaryMailService.successRegister(driver);

                //account.setProduct(productRepository.findProductById(wrapper.getProduct()));
                //account.setDateCreate(LocalDateTime.now());


                //registeredAccounts.add(account);
                //usedProxies.add(proxyProduct);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        //TODO save used proxies
        //todo save registered accounts

        orderProduct.setUser(userService.getCurrentUser());
        orderProduct.setDateOrder(LocalDateTime.now());
        orderProduct.setDateClose(LocalDateTime.now());
        orderProduct.setDatePayed(LocalDateTime.now());
        orderProduct.setIsPayed(true);
        orderProduct.setCount(wrapper.getCount());

        //todo save order product
        //orderProduct.setProxies(usedProxies);
        //orderProduct.setAccounts(registeredAccounts);

        return null; //TODO
    }

    public void temporaryMailFromBot(String temporaryMail) {
        log.info("TEMPORARY MAIL {}", temporaryMail);
    }

    public OrderProductWrapper get(Long id) {
        OrderProduct orderProduct = orderProductRepository.findOrderProductById(id);

        //TODO add id order to message
        if (orderProduct == null)
            throw new IllegalArgumentException("Заказ не найден");

        return new OrderProductWrapper(orderProduct);
    }

    public List<OrderProductWrapper> list(OrderAccountFilter filter) {
        //TODO build specification
        return null;
    }

    public void delete(Long id) {
        OrderProduct orderProduct = orderProductRepository.findOrderProductById(id);

        //TODO add id order to message
        if (orderProduct == null)
            throw new IllegalArgumentException("Заказ не найден");

        orderProduct.setDateClose(LocalDateTime.now());
        orderProductRepository.save(orderProduct);
    }

}
