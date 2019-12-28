package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.OrderWrapper;
import ru.starokozhev.SocialManager.dto.TemporaryMailWrapper;
import ru.starokozhev.SocialManager.dto.filter.OrderAccountFilter;
import ru.starokozhev.SocialManager.dto.OrderProductWrapper;
import ru.starokozhev.SocialManager.entity.OrderProduct;
import ru.starokozhev.SocialManager.repository.OrderProductRepository;
import ru.starokozhev.SocialManager.service.mail.TemporaryMailService;
import ru.starokozhev.SocialManager.service.registrator.InstagramService;
import ru.starokozhev.SocialManager.service.selenium.SeleniumService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;
    private final InstagramService instagramService;
    private final TemporaryMailService temporaryMailService;
    private final SeleniumService seleniumService;

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

        SeleniumService.createAndStartService();
        seleniumService.createDriver();
        TemporaryMailWrapper temporaryMail = temporaryMailService.getTemporaryMail(seleniumService.getDriver());
        instagramService.registerAccount(temporaryMail, seleniumService.getDriver());

        /*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--proxy-server=http://user:password@proxy.com:8080"));
        WebDriver driver = new ChromeDriverService.Builder();*/
        //TemporaryMailWrapper temporaryMail = temporaryMailService.getTemporaryMail();
        //instagramService.registerAccount(temporaryMail);

        /*switch (wrapper.getType()) {
            case RAMBLER:
                break;
            case INSTAGRAM:
                instagramRegisterService.register(1L);
                break;
            default:
                break;
        }*/

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
