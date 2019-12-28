package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.OrderProduct;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderProductWrapper {

    private Long id;

    private List<ProductWrapper> accounts;

    private List<ProxyAccountWrapper> proxies;

    private UserWrapper user;

    private Double totalPrice;

    private Long count;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateOrder;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime datePayed;

    private Boolean isPayed;

    public OrderProductWrapper(OrderProduct orderProduct) {
        toWrapper(orderProduct);
    }

    private void toWrapper(OrderProduct orderProduct) {
        if (orderProduct != null) {
            id = orderProduct.getId();
            accounts = orderProduct.getProducts().stream().map(ProductWrapper::new).collect(Collectors.toList());
            proxies = orderProduct.getProxies().stream().map(ProxyAccountWrapper::new).collect(Collectors.toList());
            user = new UserWrapper(orderProduct.getUser());
            totalPrice = orderProduct.getTotalPrice();
            count = orderProduct.getCount();
            dateOrder = orderProduct.getDateOrder();
            datePayed = orderProduct.getDatePayed();
            isPayed = orderProduct.getIsPayed();
        }
    }

    public void fromWrapper(OrderProduct orderProduct) {
        if (orderProduct != null) {
            orderProduct.setTotalPrice(totalPrice);
            orderProduct.setCount(count);
        }
    }

}
