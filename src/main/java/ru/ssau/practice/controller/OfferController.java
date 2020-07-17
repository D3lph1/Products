package ru.ssau.practice.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.practice.dto.NewOfferDTO;
import ru.ssau.practice.dto.NewOfferOfProductDTO;
import ru.ssau.practice.ex.NotFoundException;
import ru.ssau.practice.service.http.ApiError;
import ru.ssau.practice.service.http.ApiResponse;
import ru.ssau.practice.service.offer.CreateOfferService;
import ru.ssau.practice.service.offer.DeleteOffersService;
import ru.ssau.practice.service.offer.GetProductOffersService;
import ru.ssau.practice.service.product.ProductNotFoundException;

import javax.validation.Valid;

@RestController
public class OfferController extends AbstractController
{
    private final GetProductOffersService getProductOffersService;

    private final CreateOfferService createOfferService;

    private final DeleteOffersService deleteOffersService;

    public OfferController(
            MessageSource messageSource,
            GetProductOffersService getProductOffersService,
            CreateOfferService createOfferService,
            DeleteOffersService deleteOffersService
    )
    {
        super(messageSource);
        this.getProductOffersService = getProductOffersService;
        this.createOfferService = createOfferService;
        this.deleteOffersService = deleteOffersService;
    }

    @GetMapping("/api/products/{productId}/offers")
    public ResponseEntity<ApiResponse> ofProduct(@PathVariable long productId)
    {
        try {
            return ResponseEntity.ok(ApiResponse.success().add("offers", getProductOffersService.get(productId)));
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("product_not_found")
                            .addError(ApiError.danger(t("products.not_found"))),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/api/offers")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody NewOfferDTO dto)
    {
        try {
            return ResponseEntity.ok(ApiResponse.success().add("offerId", createOfferService.create(dto)));
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("product_not_found")
                        .addError(ApiError.danger(t("products.not_found"))),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/api/offers")
    public ResponseEntity<ApiResponse> delete(@RequestParam("ids") long[] IDs) throws Exception
    {
        try {
            deleteOffersService.delete(IDs);

            return ResponseEntity.ok(ApiResponse.success());
        } catch (NotFoundException e) {
            return new ResponseEntity<>(
                    ApiResponse.fail("offer_not_found")
                            .addError(ApiError.danger(t("products.few_not_found"))),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping("/api/offers/validate")
    public ApiResponse validate(@Valid @RequestBody NewOfferOfProductDTO offer)
    {
        return ApiResponse.success();
    }
}
