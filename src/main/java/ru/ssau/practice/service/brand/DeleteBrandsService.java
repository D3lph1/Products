package ru.ssau.practice.service.brand;

import org.springframework.stereotype.Service;
import ru.ssau.practice.repository.brand.BrandRepository;
import ru.ssau.practice.service.AbstractDeleteService;

@Service
public class DeleteBrandsService extends AbstractDeleteService
{
    public DeleteBrandsService(BrandRepository brandRepository)
    {
        super(brandRepository);
    }
}
