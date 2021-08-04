package com.dio.productcatalog.controller;

import com.dio.productcatalog.dto.MessageDTO;
import com.dio.productcatalog.dto.ProductDTO;
import com.dio.productcatalog.exception.DifferentIdsException;
import com.dio.productcatalog.exception.ProductNotFoundException;
import com.dio.productcatalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO create(@RequestBody @Valid ProductDTO productDTO) {
        return productService.create(productDTO);
    }

    @GetMapping
    public List<ProductDTO> getProductList() {
        return productService.findAll();
    }

    @GetMapping("/{productId}")
    public ProductDTO getProductById(@PathVariable("productId") Long productId) throws ProductNotFoundException {
        return productService.findById(productId);
    }

    @PutMapping("/{productId}")
    public MessageDTO updateProductById(@PathVariable("productId") Long productId
            , @RequestBody @Valid ProductDTO productDTO) throws ProductNotFoundException, DifferentIdsException {
        return productService.updateById(productId, productDTO);
    }
}
