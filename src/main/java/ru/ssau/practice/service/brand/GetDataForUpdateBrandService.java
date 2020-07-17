package ru.ssau.practice.service.brand;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.UpdateBrandDTO;
import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.repository.brand.BrandRepository;

import java.util.Optional;

@Service
public class GetDataForUpdateBrandService
{
    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

    public GetDataForUpdateBrandService(BrandRepository brandRepository, ModelMapper modelMapper)
    {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    public UpdateBrandDTO get(long brandId) throws BrandNotFoundException
    {
        Optional<Brand> mbBrand = brandRepository.findById(brandId);
        if (!mbBrand.isPresent()) {
            throw BrandNotFoundException.byId(brandId);
        }

        return modelMapper.map(mbBrand.get(), UpdateBrandDTO.class);
    }
}
