package com.ams.SimilarProducts.response;

import com.ams.SimilarProducts.model.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class SimilarProductsResponse {
    private ProductDetail productDetail;
}