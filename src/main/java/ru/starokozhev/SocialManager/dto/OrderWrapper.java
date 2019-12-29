package ru.starokozhev.SocialManager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.enums.ProductName;

@Data
@NoArgsConstructor
public class OrderWrapper {

    private Long count;
    private long product;

}
