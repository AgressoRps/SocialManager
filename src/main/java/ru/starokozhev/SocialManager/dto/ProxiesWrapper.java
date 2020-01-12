package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Proxies;

@Data
@NoArgsConstructor
public class ProxiesWrapper {

    private Long id;

    private Long warning;

    private Long success;

    private Long danger;

    public ProxiesWrapper(Proxies proxies) {
        toWrapper(proxies);
    }

    private void toWrapper(Proxies proxies) {
        if (proxies != null) {
            id = proxies.getId();
            warning = proxies.getWarning();
            success = proxies.getSuccess();
            danger = proxies.getDanger();
        }
    }

    public void fromWrapper(Proxies proxies) {
        if (proxies != null) {
            proxies.setWarning(warning);
            proxies.setSuccess(success);
            proxies.setDanger(danger);
        }
    }

}
