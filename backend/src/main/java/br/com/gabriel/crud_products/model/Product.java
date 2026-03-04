package br.com.gabriel.crud_products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Nome não pode ser vazio")
    private String name;
    @NotNull(message="Preço não pode ser nulo")
    private Double price;
    @NotNull(message="Quantidade não pode ser nula")
    private Integer quantity;



}
