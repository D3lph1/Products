package ru.ssau.practice.service.product;

import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.NewProductDTO;
import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.entity.Product;
import ru.ssau.practice.repository.brand.BrandRepository;
import ru.ssau.practice.repository.product.ProductRepository;
import ru.ssau.practice.service.brand.BrandNotFoundException;

import java.util.Optional;

@Service
public class UpdateProductService
{
    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    public UpdateProductService(ProductRepository productRepository, BrandRepository brandRepository)
    {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
    }

    public void update(long productId, NewProductDTO dto) throws
            ProductNotFoundException,
            BrandNotFoundException,
            ProductWithArticleAndBrandAlreadyExistsException,
            ProductWithBarcodeAlreadyExistsException
    {
        Optional<Product> mbProduct = productRepository.findById(productId);
        if (!mbProduct.isPresent()) {
            throw ProductNotFoundException.byId(productId);
        }

        Optional<Brand> mbBrand = brandRepository.findById(dto.getBrand());
        if (!mbBrand.isPresent()) {
            throw BrandNotFoundException.byId(dto.getBrand());
        }

        Product product = mbProduct.get();
        Brand brand = mbBrand.get();

        if (productRepository.existsByArticleAndBrandExcept(dto.getArticle(), brand, product)) {
            throw new ProductWithArticleAndBrandAlreadyExistsException(dto.getArticle(), brand);
        }

        if (productRepository.existsByBarcodeExcept(dto.getBarcode(), product)) {
            throw new ProductWithBarcodeAlreadyExistsException(dto.getBarcode());
        }


        product.rename(dto.getName());
        product.setArticle(dto.getArticle());
        product.setBarcode(dto.getBarcode());


        product.setBrand(mbBrand.get());

        productRepository.save(product);
    }
}
