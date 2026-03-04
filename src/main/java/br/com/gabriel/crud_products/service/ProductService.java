package br.com.gabriel.crud_products.service;

import br.com.gabriel.crud_products.model.Product;
import br.com.gabriel.crud_products.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.List;

@Service
public class ProductService {

    // usando o constructor injection para fazer a injeção de dependencia invés de field injection
    private final ProductRepository repository;
    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<Product> getProducts(){
        return repository.findAll();
    }

    public Product getIdProducts(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    public Product postProducts(Product product){
        return repository.save(product);
    }

    public Product updateProducts(Long id, Product product){
        Product updateProducts = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        updateProducts.setName(product.getName());
        updateProducts.setPrice(product.getPrice());

        return repository.save(updateProducts);
    }

    public void deleteProducts(Long id){
        repository.deleteById(id);
    }


}
