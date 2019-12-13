package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.OrderAccount;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderAccountWrapper {

    private Long id;

    private List<AccountWrapper> accounts;

    private List<ProxyAccountWrapper> proxies;

    private UserWrapper user;

    private Double totalPrice;

    private Long count;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yy HH:mm")
    private LocalDateTime dateOrder;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yy HH:mm")
    private LocalDateTime datePayed;

    private Boolean isPayed;

    public OrderAccountWrapper(OrderAccount orderAccount) {
        toWrapper(orderAccount);
    }

    private void toWrapper(OrderAccount orderAccount) {
        if (orderAccount != null) {
            id = orderAccount.getId();
            accounts = orderAccount.getAccounts().stream().map(AccountWrapper::new).collect(Collectors.toList());
            proxies = orderAccount.getProxies().stream().map(ProxyAccountWrapper::new).collect(Collectors.toList());
            user = new UserWrapper(orderAccount.getUser());
            totalPrice = orderAccount.getTotalPrice();
            count = orderAccount.getCount();
            dateOrder = orderAccount.getDateOrder();
            datePayed = orderAccount.getDatePayed();
            isPayed = orderAccount.getIsPayed();
        }
    }

    public void fromWrapper(OrderAccount orderAccount) {
        if (orderAccount != null) {
            orderAccount.setTotalPrice(totalPrice);
            orderAccount.setCount(count);
        }
    }

}
