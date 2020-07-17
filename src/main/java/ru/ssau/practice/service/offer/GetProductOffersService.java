package ru.ssau.practice.service.offer;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.OfferDTO;
import ru.ssau.practice.entity.Offer;
import ru.ssau.practice.entity.Product;
import ru.ssau.practice.repository.offer.OfferRepository;
import ru.ssau.practice.repository.product.ProductRepository;
import ru.ssau.practice.service.product.ProductNotFoundException;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetProductOffersService
{
    private final ProductRepository productRepository;

    private final OfferRepository offerRepository;

    private final ModelMapper modelMapper;

    public GetProductOffersService(
            ProductRepository productRepository,
            OfferRepository offerRepository,
            ModelMapper modelMapper
    )
    {
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    public Collection<OfferDTO> get(long id) throws ProductNotFoundException
    {
        Optional<Product> mbProduct = productRepository.findById(id);
        if (!mbProduct.isPresent()) {
            throw ProductNotFoundException.byId(id);
        }

        Collection<Offer> offers = offerRepository.getByProduct(mbProduct.get());

        return offers
                .stream()
                .map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
    }
}
