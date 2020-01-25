package ru.starokozhev.SocialManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.starokozhev.SocialManager.entity.Earned;

import java.util.List;

public interface EarnedRepository extends JpaRepository<Earned, Long> {

    Earned findEarnedById(Long id);

}
