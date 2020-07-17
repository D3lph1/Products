package ru.ssau.practice.service.offer;

import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.NewOfferDTO;
import ru.ssau.practice.entity.Offer;
import ru.ssau.practice.entity.Product;
import ru.ssau.practice.repository.offer.OfferRepository;
import ru.ssau.practice.repository.product.ProductRepository;
import ru.ssau.practice.service.product.ProductNotFoundException;

import java.util.Optional;

@Service
public class CreateOfferService
{
    private final OfferRepository offerRepository;

    private final ProductRepository productRepository;

    public CreateOfferService(OfferRepository offerRepository, ProductRepository productRepository)
    {
        this.offerRepository = offerRepository;
        this.productRepository = productRepository;
    }

    public long create(NewOfferDTO dto) throws ProductNotFoundException
    {
        Optional<Product> mbProduct = productRepository.findById(dto.getProduct());
        if (!mbProduct.isPresent()) {
            throw ProductNotFoundException.byId(dto.getProduct());
        }

        Product product = mbProduct.get();

        Offer offer = new Offer(product, dto.getPrice(), dto.getActualUntil());
        offerRepository.save(offer);

        return offer.getId();
    }
}
