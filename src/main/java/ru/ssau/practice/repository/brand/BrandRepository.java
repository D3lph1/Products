package ru.ssau.practice.repository.brand;

import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends BrandRepositoryBasic, BrandRepositoryCustom
{
}
