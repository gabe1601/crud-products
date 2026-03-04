package br.com.gabriel.crud_products.service;

import br.com.gabriel.crud_products.dto.ProductCreateDTO;
import br.com.gabriel.crud_products.dto.ProductResponseDTO;
import br.com.gabriel.crud_products.dto.ProductUpdateDTO;
import br.com.gabriel.crud_products.model.Product;
import br.com.gabriel.crud_products.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class ProductService {

    // usando o constructor injection para fazer a injeção de dependencia invés de field injection
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<ProductResponseDTO> getIdProducts(Long id){
        return repository.findById(id).stream().map(ProductResponseDTO::fromEntity).toList();
    }

    public void save(ProductCreateDTO dto){
        Product product = dto.toEntity();
        repository.save(product);
    }

    public ResponseEntity updateProducts(Long id, ProductUpdateDTO dto){
        Product updateProducts = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if(dto.getName() != null){
            updateProducts.setName(dto.getName());
        }
        if(dto.getPrice() != null) {
            updateProducts.setPrice(dto.getPrice());
        }

        repository.save(updateProducts);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{/id}")
    public void deleteProducts(Long id){
        repository.deleteById(id);
    }


    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {

        Page<Product> page = repository.findAll(pageable);

        return page.map(ProductResponseDTO::fromEntity);

    }

    public List<ProductResponseDTO> getProductName(String name) {
        return ;
    }
}
