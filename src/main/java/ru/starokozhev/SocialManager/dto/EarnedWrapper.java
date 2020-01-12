package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Earned;

@Data
@NoArgsConstructor
public class EarnedWrapper {

    private Long id;

    private Long week;

    private Long day;

    private Long month;

    public EarnedWrapper(Earned earned) {
        toWrapper(earned);
    }

    private void toWrapper(Earned earned) {
        if (earned != null) {
            id = earned.getId();
            week = earned.getWeek();
            day = earned.getDay();
            month = earned.getMonth();
        }
    }

    public void fromWrapper(Earned earned) {
        if (earned != null) {
            earned.setWeek(week);
            earned.setDay(day);
            earned.setMonth(month);
        }
    }

}
