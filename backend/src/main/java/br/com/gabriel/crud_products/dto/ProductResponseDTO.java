package br.com.gabriel.crud_products.dto;

import br.com.gabriel.crud_products.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {

    private Long id;
    private String name;
    private Double price;
    private Integer quantity;

    public static ProductResponseDTO fromEntity (Product product){

        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());

        return dto;
    }

}
