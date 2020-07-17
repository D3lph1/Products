package ru.ssau.practice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.practice.dto.NewProductDTO;
import ru.ssau.practice.ex.NotFoundException;
import ru.ssau.practice.service.brand.BrandNotFoundException;
import ru.ssau.practice.service.db.pagination.PaginationRequest;
import ru.ssau.practice.service.http.ApiError;
import ru.ssau.practice.service.http.ApiResponse;
import ru.ssau.practice.service.product.*;

import javax.validation.Valid;

@RestController
public class ProductController
{
    private final ProductsListService productsListService;

    private final DeleteProductsService deleteProductsService;

    private final CreateProductService createProductService;

    private final GetDataForUpdateProductService getDataForUpdateProductService;

    private final UpdateProductService updateProductService;

    public ProductController(
            ProductsListService productsListService,
            DeleteProductsService deleteProductsService,
            CreateProductService createProductService,
            GetDataForUpdateProductService getDataForUpdateProductService,
            UpdateProductService updateProductService
    )
    {
        this.productsListService = productsListService;
        this.deleteProductsService = deleteProductsService;
        this.createProductService = createProductService;
        this.getDataForUpdateProductService = getDataForUpdateProductService;
        this.updateProductService = updateProductService;
    }

    @GetMapping("/api/products")
    public ResponseEntity<ApiResponse> list(@Valid PaginationRequest request)
    {
        return new ResponseEntity<>(
                ApiResponse.success().add("products", productsListService.list(request)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/api/products")
    public ResponseEntity<ApiResponse> delete(@RequestParam("ids") long[] IDs) throws Exception
    {
        try {
            deleteProductsService.delete(IDs);

            return new ResponseEntity<>(
                    ApiResponse.success().addError(ApiError.success("Product(s) successfully deleted")),
                    HttpStatus.OK
            );
        } catch (NotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("product_not_found")
                            .addError(ApiError.danger("One or more products not found"))
                            .add("products", e.getReason()),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/api/products")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody NewProductDTO dto)
    {
        try {
            createProductService.create(dto);

            return new ResponseEntity<>(
                    ApiResponse.success()
                            .addError(ApiError.success("Product has been successfully created")),
                    HttpStatus.OK
            );
        } catch (BrandNotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("brand_not_found")
                            .addError(ApiError.danger("Brand not found")),
                    HttpStatus.NOT_FOUND
            );
        } catch (ProductWithArticleAndBrandAlreadyExistsException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("product_with_aticle_and_brand_already_exists")
                            .addError(ApiError.danger("Product with specified article and brand combination already exists")),
                    HttpStatus.CONFLICT
            );
        } catch (ProductWithBarcodeAlreadyExistsException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("product_with_barcode_already_exists")
                            .addError(ApiError.danger("Product with specified barcode already exists")),
                    HttpStatus.CONFLICT
            );
        }
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<ApiResponse> dataForUpdate(@PathVariable long productId)
    {
        try {
            return ResponseEntity.ok(ApiResponse.success().add("product", getDataForUpdateProductService.get(productId)));
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("product_not_found")
                        .addError(ApiError.danger("Product not found")),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/api/products/{productId}")
    public ResponseEntity<ApiResponse> update(@PathVariable long productId, @Valid @RequestBody NewProductDTO dto)
    {
        try {
            updateProductService.update(productId, dto);

            return ResponseEntity.ok(ApiResponse.success().addError(ApiError.success("Product has been successfully updated")));
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("product_not_found")
                            .addError(ApiError.danger("Product not found")),
                    HttpStatus.NOT_FOUND
            );
        } catch (BrandNotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("brand_not_found")
                            .addError(ApiError.danger("Brand not found")),
                    HttpStatus.NOT_FOUND
            );
        } catch (ProductWithArticleAndBrandAlreadyExistsException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("product_with_aticle_and_brand_already_exists")
                            .addError(ApiError.danger("Product with specified article and brand combination already exists")),
                    HttpStatus.CONFLICT
            );
        } catch (ProductWithBarcodeAlreadyExistsException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("product_with_barcode_already_exists")
                            .addError(ApiError.danger("Product with specified barcode already exists")),
                    HttpStatus.CONFLICT
            );
        }
    }
}
