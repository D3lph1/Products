package ru.ssau.practice.service.offer;

import org.springframework.stereotype.Service;
import ru.ssau.practice.ex.NotFoundException;
import ru.ssau.practice.repository.offer.OfferRepository;
import ru.ssau.practice.service.AbstractDeleteService;

@Service
public class DeleteOffersService extends AbstractDeleteService
{
    public DeleteOffersService(OfferRepository offerRepository)
    {
        super(offerRepository);
    }
}
