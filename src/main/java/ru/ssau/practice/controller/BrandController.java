package ru.ssau.practice.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.practice.dto.NewBrandDTO;
import ru.ssau.practice.ex.NotFoundException;
import ru.ssau.practice.service.brand.*;
import ru.ssau.practice.service.db.pagination.PaginationRequest;
import ru.ssau.practice.service.http.ApiError;
import ru.ssau.practice.service.http.ApiResponse;

import javax.validation.Valid;

@RestController
public class BrandController extends AbstractController
{
    private final BrandsListService brandsListService;

    private final DeleteBrandsService deleteProductsService;

    private final GetAllBrandsService getAllBrandsService;

    private final CreateBrandService createBrandService;

    private final GetDataForUpdateBrandService getDataForUpdateBrandService;

    private final UpdateBrandService updateBrandService;

    public BrandController(
            MessageSource messageSource,
            BrandsListService brandsListService,
            DeleteBrandsService deleteBrandsService,
            GetAllBrandsService getAllBrandsService,
            CreateBrandService createBrandService,
            GetDataForUpdateBrandService getDataForUpdateBrandService,
            UpdateBrandService updateBrandService
    )
    {
        super(messageSource);
        this.brandsListService = brandsListService;
        this.deleteProductsService = deleteBrandsService;
        this.getAllBrandsService = getAllBrandsService;
        this.createBrandService = createBrandService;
        this.getDataForUpdateBrandService = getDataForUpdateBrandService;
        this.updateBrandService = updateBrandService;
    }

    @GetMapping("/api/brands")
    public ApiResponse list(@Valid PaginationRequest request)
    {
        return ApiResponse.success().add("brands", brandsListService.list(request));
    }

    @DeleteMapping("/api/brands")
    public ResponseEntity<ApiResponse> delete(@RequestParam("ids") long[] IDs) throws Exception
    {
        try {
            deleteProductsService.delete(IDs);

            return ResponseEntity.ok(ApiResponse.success().addError(ApiError.success(t("brands.delete.success"))));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("brand_not_found")
                            .addError(ApiError.danger(t("brands.few_not_found")))
                            .add("brands", e.getReason()),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/api/brands/all")
    public ApiResponse all()
    {
        return ApiResponse.success().add("brands", getAllBrandsService.get());
    }

    @PostMapping("/api/brands")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody NewBrandDTO dto)
    {
        try {
            createBrandService.create(dto);

            return ResponseEntity.ok(ApiResponse.success().addError(ApiError.success(t("brands.create.success"))));
        } catch (BrandAlreadyExistsException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("brand_already_exists")
                        .addError(ApiError.danger(t("brands.name.already_exists"))),
                    HttpStatus.CONFLICT
            );
        }
    }

    @GetMapping("/api/brands/{brandId}")
    public ResponseEntity<ApiResponse> dataForUpdate(@PathVariable long brandId)
    {
        try {
            return ResponseEntity.ok(ApiResponse.success().add("brand", getDataForUpdateBrandService.get(brandId)));
        } catch (BrandNotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("brand_not_found")
                            .addError(ApiError.danger(t("brands.not_found"))),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/api/brands/{brandId}")
    public ResponseEntity<ApiResponse> update(@PathVariable long brandId, @Valid @RequestBody NewBrandDTO dto)
    {
        try {
            updateBrandService.update(brandId, dto);

            return ResponseEntity.ok(ApiResponse.success().addError(ApiError.success(t("brands.update.success"))));
        } catch (BrandNotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("brand_not_found")
                            .addError(ApiError.danger(t("brands.not_found"))),
                    HttpStatus.NOT_FOUND
            );
        } catch (BrandAlreadyExistsException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("brand_already_exists")
                            .addError(ApiError.danger(t("brands.name.already_exists"))),
                    HttpStatus.CONFLICT
            );
        }
    }
}
