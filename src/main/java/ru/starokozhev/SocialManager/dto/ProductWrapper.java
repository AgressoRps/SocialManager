package ru.starokozhev.SocialManager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.starokozhev.SocialManager.entity.Product;
import ru.starokozhev.SocialManager.enums.ProductName;
import ru.starokozhev.SocialManager.enums.ProductType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductWrapper {

    private Long id;

    private ProductName name;

    private ProductType type;

    private Double price;

    private String url;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.mm.yyyy HH:mm")
    private LocalDateTime dateCreate;

    public ProductWrapper(Product product) {
        toWrapper(product);
    }

    private void toWrapper(Product product) {
        if (product != null) {
            id = product.getId();
            name = product.getName();
            type = product.getType();
            price = product.getPrice();
            url = product.getUrl();
            dateCreate = product.getDateCreate();
        }
    }

    public void fromWrapper(Product product) {
        if (product != null) {
            product.setName(name);
            product.setType(type);
            product.setPrice(price);
            product.setUrl(url);
        }
    }

}
