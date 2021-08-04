package com.dio.productcatalog.service;

import com.dio.productcatalog.dto.MessageDTO;
import com.dio.productcatalog.dto.ProductDTO;
import com.dio.productcatalog.entity.Product;
import com.dio.productcatalog.exception.DifferentIdsException;
import com.dio.productcatalog.exception.ProductNotFoundException;
import com.dio.productcatalog.mapper.ProductMapper;
import com.dio.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    public MessageDTO create(ProductDTO productDTO) {
        Product productToSave = productMapper.toModel(productDTO);

        Product createdProduct = productRepository.save(productToSave);

        return MessageDTO.builder()
                .msg(String.format("Created %s with id: %d", createdProduct.getName(), createdProduct.getId()))
                .build();
    }

    public List<ProductDTO> findAll() {

        return productRepository.findAll().stream()
                .map(productMapper::toDTO).collect(Collectors.toList());
    }

    public ProductDTO findById(Long productId) throws ProductNotFoundException {
        Product product = verifyById(productId);

        return productMapper.toDTO(product);
    }

    public MessageDTO updateById(Long productId, ProductDTO productDTO) throws ProductNotFoundException, DifferentIdsException {
        verifyById(productId);

        if (!productId.equals(productDTO.getId())) {
            throw new DifferentIdsException(productId, productDTO.getId());
        }

        Product updatedProduct = productRepository.save(productMapper.toModel(productDTO));

        return MessageDTO.builder()
                .msg(String.format("Updated %s with Id: %d", updatedProduct.getName(), updatedProduct.getId()))
                .build();
    }

    private Product verifyById(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

}
