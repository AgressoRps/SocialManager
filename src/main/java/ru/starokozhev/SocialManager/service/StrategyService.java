package ru.starokozhev.SocialManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.starokozhev.SocialManager.dto.StrategyWrapper;
import ru.starokozhev.SocialManager.dto.filter.StrategyFilter;
import ru.starokozhev.SocialManager.entity.Strategy;
import ru.starokozhev.SocialManager.repository.StrategyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StrategyService {

    private final StrategyRepository strategyRepository;

    public StrategyWrapper add(StrategyWrapper wrapper) {
        return null;
    }

    public StrategyWrapper edit(StrategyWrapper wrapper) {
        return null;
    }

    public StrategyWrapper get(String name) {
        return null;
    }

    public void delete(String name) {

    }

    public List<StrategyWrapper> list(StrategyFilter filter) {
        return null;
    }

}
