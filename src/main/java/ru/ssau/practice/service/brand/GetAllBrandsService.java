package ru.ssau.practice.service.brand;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.BrandDTO;
import ru.ssau.practice.repository.brand.BrandRepository;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class GetAllBrandsService
{
    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

    public GetAllBrandsService(BrandRepository brandRepository, ModelMapper modelMapper)
    {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    public Collection<BrandDTO> get()
    {
        return StreamSupport.stream(brandRepository.findAll().spliterator(), false)
            .map(brand -> modelMapper.map(brand, BrandDTO.class))
                .collect(Collectors.toList());
    }
}
