package ru.ssau.practice.service.product;

import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.NewOfferOfProductDTO;
import ru.ssau.practice.dto.NewProductDTO;
import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.entity.Offer;
import ru.ssau.practice.entity.Product;
import ru.ssau.practice.repository.brand.BrandRepository;
import ru.ssau.practice.repository.offer.OfferRepository;
import ru.ssau.practice.repository.product.ProductRepository;
import ru.ssau.practice.service.brand.BrandNotFoundException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CreateProductService
{
    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final OfferRepository offerRepository;

    public CreateProductService(
            ProductRepository productRepository,
            BrandRepository brandRepository,
            OfferRepository offerRepository
    )
    {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.offerRepository = offerRepository;
    }

    @Transactional
    public void create(NewProductDTO dto) throws
            BrandNotFoundException,
            ProductWithArticleAndBrandAlreadyExistsException,
            ProductWithBarcodeAlreadyExistsException
    {
        Optional<Brand> mbBrand = brandRepository.findById(dto.getBrand());
        if (!mbBrand.isPresent()) {
            throw BrandNotFoundException.byId(dto.getBrand());
        }

        Brand brand = mbBrand.get();

        if (productRepository.existsByArticleAndBrand(dto.getArticle(), brand)) {
            throw new ProductWithArticleAndBrandAlreadyExistsException(dto.getArticle(), brand);
        }

        if (productRepository.existsByBarcode(dto.getBarcode())) {
            throw new ProductWithBarcodeAlreadyExistsException(dto.getBarcode());
        }

        Product product = new Product(dto.getName(), brand, dto.getArticle(), dto.getBarcode());

        for (NewOfferOfProductDTO offerDTO : dto.getOffers()) {
            Offer offer = new Offer(product, offerDTO.getPrice(), offerDTO.getActualUntil());
            offerRepository.save(offer);
        }

        productRepository.save(product);
    }
}
