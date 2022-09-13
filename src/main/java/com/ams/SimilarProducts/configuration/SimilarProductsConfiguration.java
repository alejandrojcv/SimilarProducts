package com.ams.SimilarProducts.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SimilarProductsConfiguration {
    @Value("${app.url.product.integration}")
    private String urlProduct;
}