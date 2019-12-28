package ru.starokozhev.SocialManager.service.registrator;

import ru.starokozhev.SocialManager.dto.ProductWrapper;

import javax.transaction.Transactional;
import java.util.List;

public interface IRegister {

    @Transactional
    public List<ProductWrapper> register(Long count);

}
