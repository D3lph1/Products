package ru.ssau.practice.service;

import org.modelmapper.ModelMapper;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilder;
import ru.ssau.practice.service.db.pagination.PaginationQueryBuilderAware;
import ru.ssau.practice.service.db.pagination.PaginationRequest;
import ru.ssau.practice.service.db.pagination.PaginationResult;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListService<E, D>
{
    private final Class<D> DTOType;

    private final PaginationQueryBuilderAware<E> paginationQueryBuilderAware;

    private final ModelMapper modelMapper;

    public AbstractListService(Class<D> DTOType, PaginationQueryBuilderAware<E> paginationQueryBuilderAware, ModelMapper modelMapper)
    {
        this.DTOType = DTOType;
        this.paginationQueryBuilderAware = paginationQueryBuilderAware;
        this.modelMapper = modelMapper;
    }

    public PaginationResult<D> list(PaginationRequest request)
    {
        PaginationResult<E> products = pagination(request, paginationQueryBuilderAware
                .createPaginationQueryBuilder())
                .order(request.getSortBy(), request.isSortDesc())
                .execute(request.getPage(), request.getPerPage());

        List<D> DTOs = new ArrayList<>();
        products.getItems().forEach(i -> DTOs.add(modelMapper.map(i, DTOType)));

        return new PaginationResult<>(DTOs, products.getPages());
    }

    protected abstract PaginationQueryBuilder<E> pagination(PaginationRequest request, PaginationQueryBuilder<E> qb);
}
