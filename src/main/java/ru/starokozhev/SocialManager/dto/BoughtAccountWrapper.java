package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;
import ru.starokozhev.SocialManager.entity.BoughtAccount;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BoughtAccountWrapper {

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

    public BoughtAccountWrapper(BoughtAccount boughtAccount) {
        toWrapper(boughtAccount);
    }

    private void toWrapper(BoughtAccount boughtAccount) {
        if (boughtAccount != null) {
            id = boughtAccount.getId();
            accounts = boughtAccount.getAccounts().stream().map(AccountWrapper::new).collect(Collectors.toList());
            proxies = boughtAccount.getProxies().stream().map(ProxyAccountWrapper::new).collect(Collectors.toList());
            user = new UserWrapper(boughtAccount.getUser());
            totalPrice = boughtAccount.getTotalPrice();
            count = boughtAccount.getCount();
            dateOrder = boughtAccount.getDateOrder();
            datePayed = boughtAccount.getDatePayed();
            isPayed = boughtAccount.getIsPayed();
        }
    }

    public void fromWrapper(BoughtAccount boughtAccount) {
        if (boughtAccount != null) {
            boughtAccount.setTotalPrice(totalPrice);
            boughtAccount.setCount(count);
        }
    }

}
