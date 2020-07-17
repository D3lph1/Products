package ru.ssau.practice.service.product;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.UpdateProductDTO;
import ru.ssau.practice.entity.Product;
import ru.ssau.practice.repository.product.ProductRepository;

import java.util.Optional;

@Service
public class GetDataForUpdateProductService
{
    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public GetDataForUpdateProductService(ProductRepository productRepository, ModelMapper modelMapper)
    {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public UpdateProductDTO get(long productId) throws ProductNotFoundException
    {
        Optional<Product> mbProduct = productRepository.findById(productId);
        if (!mbProduct.isPresent()) {
            throw ProductNotFoundException.byId(productId);
        }

        return modelMapper.map(mbProduct.get(), UpdateProductDTO.class);
    }
}
