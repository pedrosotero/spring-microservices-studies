package com.dio.productcatalog.mapper;

import com.dio.productcatalog.dto.ProductDTO;
import com.dio.productcatalog.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toModel(ProductDTO productDTO);

    ProductDTO toDTO(Product product);
}
