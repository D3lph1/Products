package ru.ssau.practice.service.product;

import org.springframework.stereotype.Service;
import ru.ssau.practice.repository.product.ProductRepository;
import ru.ssau.practice.service.AbstractDeleteService;

@Service
public class DeleteProductsService extends AbstractDeleteService
{
    public DeleteProductsService(ProductRepository productRepository)
    {
        super(productRepository);
    }
}
