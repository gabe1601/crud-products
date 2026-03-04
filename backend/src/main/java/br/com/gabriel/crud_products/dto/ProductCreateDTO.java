package br.com.gabriel.crud_products.dto;

import br.com.gabriel.crud_products.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDTO {

    private String name;
    private Double price;
    private Integer quantity;


    //metodo para conversão

    public Product toEntity(){

        Product product = new Product();

        product.setName(this.getName());
        product.setQuantity(this.getQuantity());
        product.setPrice(this.getPrice());

        return product;
    }
}
