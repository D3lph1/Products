package ru.ssau.practice.service.brand;

import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.NewBrandDTO;
import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.repository.brand.BrandRepository;

import java.util.Optional;

@Service
public class UpdateBrandService
{
    private final BrandRepository brandRepository;

    public UpdateBrandService(BrandRepository brandRepository)
    {
        this.brandRepository = brandRepository;
    }

    public void update(long brandId, NewBrandDTO dto) throws BrandNotFoundException, BrandAlreadyExistsException
    {
        Optional<Brand> mbBrand = brandRepository.findById(brandId);
        if (!mbBrand.isPresent()) {
            throw BrandNotFoundException.byId(brandId);
        }

        Brand brand = mbBrand.get();

        if (brandRepository.existsByNameExcept(dto.getName(), brand)) {
            throw BrandAlreadyExistsException.withName(dto.getName());
        }

        brand.rename(dto.getName());
        brandRepository.save(brand);
    }
}
