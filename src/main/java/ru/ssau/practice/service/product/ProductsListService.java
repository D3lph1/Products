package ru.ssau.practice.service.product;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.ProductDTO;
import ru.ssau.practice.entity.Product;
import ru.ssau.practice.repository.product.ProductPaginationQueryBuilder;
import ru.ssau.practice.repository.product.ProductRepository;
import ru.ssau.practice.service.AbstractListService;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilder;
import ru.ssau.practice.service.db.pagination.PaginationRequest;

@Service
public class ProductsListService extends AbstractListService<Product, ProductDTO>
{
    private ProductRepository productRepository;

    private ModelMapper modelMapper;

    public ProductsListService(ProductRepository productRepository, ModelMapper modelMapper)
    {
        super(ProductDTO.class, productRepository, modelMapper);
    }

    @Override
    protected PaginationQueryBuilder<Product> pagination(PaginationRequest request, PaginationQueryBuilder<Product> qb)
    {
        return ((ProductPaginationQueryBuilder) qb)
                .whereNameLike(request.getFilter())
                .whereArticleLike(request.getFilter())
                .whereBrandNameLike(request.getFilter());
    }
}
