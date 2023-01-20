package com.ms.example.product;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Slf4j
@Validated
@RestController
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/import")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Import products", response = ProductImportResponse.class, responseContainer = "Set")
    public Set<ProductImportResponse> importProducts(
            @RequestBody @Valid @Size(max = 512) Collection<ProductImportRequest> products
    ) {
        return productService.importProducts(products);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get product details", response = ProductGetResponse.class)
    public ProductGetResponse getProduct(
            @Valid @Positive @PathVariable(name = "id") long id
    ) {
        return productService.getProductDetails(id);
    }
}
