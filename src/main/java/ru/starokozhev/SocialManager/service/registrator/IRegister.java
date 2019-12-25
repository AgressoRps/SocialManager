package ru.starokozhev.SocialManager.service.registrator;

import ru.starokozhev.SocialManager.dto.AccountWrapper;

import javax.transaction.Transactional;
import java.util.List;

public interface IRegister {

    @Transactional
    public List<AccountWrapper> register(Long count);

}
