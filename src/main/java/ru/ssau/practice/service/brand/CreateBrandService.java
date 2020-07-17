package ru.ssau.practice.service.brand;

import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.NewBrandDTO;
import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.repository.brand.BrandRepository;

@Service
public class CreateBrandService
{
    private final BrandRepository brandRepository;

    public CreateBrandService(BrandRepository brandRepository)
    {
        this.brandRepository = brandRepository;
    }

    public void create(NewBrandDTO dto) throws BrandAlreadyExistsException
    {
        if (brandRepository.existsByName(dto.getName())) {
            throw BrandAlreadyExistsException.withName(dto.getName());
        }

        brandRepository.save(new Brand(dto.getName()));
    }
}
