package ru.ssau.practice.service.brand;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ssau.practice.dto.BrandDTO;
import ru.ssau.practice.entity.Brand;
import ru.ssau.practice.repository.brand.BrandPaginationQueryBuilder;
import ru.ssau.practice.repository.brand.BrandRepository;
import ru.ssau.practice.service.AbstractListService;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilder;
import ru.ssau.practice.service.db.pagination.PaginationRequest;

@Service
public class BrandsListService extends AbstractListService<Brand, BrandDTO>
{
    public BrandsListService(BrandRepository brandRepository, ModelMapper modelMapper)
    {
        super(BrandDTO.class, brandRepository, modelMapper);
    }

    @Override
    protected PaginationQueryBuilder<Brand> pagination(PaginationRequest request, PaginationQueryBuilder<Brand> qb)
    {
        return ((BrandPaginationQueryBuilder) qb)
                .whereNameLike(request.getFilter());
    }
}
