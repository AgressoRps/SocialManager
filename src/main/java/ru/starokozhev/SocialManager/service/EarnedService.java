package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.EarnedWrapper;
import ru.starokozhev.SocialManager.entity.Earned;
import ru.starokozhev.SocialManager.repository.EarnedRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EarnedService {

    private final EarnedRepository earnedRepository;

    public EarnedWrapper add(EarnedWrapper wrapper) {
        Earned earned = new Earned();
        wrapper.fromWrapper(earned);

        return new EarnedWrapper(earnedRepository.save(earned));
    }

    public EarnedWrapper edit(EarnedWrapper wrapper) {
        Earned earned = earnedRepository.findEarnedById(wrapper.getId());

        if (earned == null)
            throw new IllegalArgumentException("Доход не найден!");

        wrapper.fromWrapper(earned);

        return new EarnedWrapper(earnedRepository.save(earned));
    }

    public EarnedWrapper get(Long id) {
        Earned earned = earnedRepository.findEarnedById(id);

        if (earned == null)
            throw new IllegalArgumentException("Доход не найден!");

        return new EarnedWrapper(earned);
    }

}
