package com.ms.example.product;

import java.util.Collection;
import java.util.Set;

public interface ProductService {

    Set<ProductImportResponse> importProducts(Collection<ProductImportRequest> products);

    ProductGetResponse getProductDetails(long id);
}
