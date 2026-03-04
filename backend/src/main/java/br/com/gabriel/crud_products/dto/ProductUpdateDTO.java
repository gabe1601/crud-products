package br.com.gabriel.crud_products.dto;

import br.com.gabriel.crud_products.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDTO {

    private Long id;
    private String name;
    private Double price;
}
