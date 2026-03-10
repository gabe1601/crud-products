package br.com.gabriel.crud_products.controller;

import br.com.gabriel.crud_products.dto.ProductCreateDTO;
import br.com.gabriel.crud_products.dto.ProductResponseDTO;
import br.com.gabriel.crud_products.dto.ProductUpdateDTO;
import br.com.gabriel.crud_products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    @ResponseBody // Retorna informação
    // PathVariable pega a parte indicada do JSON e usa no controller
    public List<ProductResponseDTO> readProductID(@PathVariable Long id){
        return productService.getIdProducts(id);
    }

    @GetMapping
    @ResponseBody
    public Page<ProductResponseDTO> readAllProducts(Pageable pageable){
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/filter")
    @ResponseBody
    public List<ProductResponseDTO> searchProdutc(@RequestParam String name){
        return productService.getProductName(name);
    }


    // Request pega informação do usuario
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ProductCreateDTO dto){
        productService.save(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ProductUpdateDTO dto ){

        return productService.updateProducts(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        productService.deleteProducts(id);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.put("validationErro",errorMessage);
        });
        return errors;
    }
}
