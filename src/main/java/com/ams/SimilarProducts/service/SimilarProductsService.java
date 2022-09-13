package com.ams.SimilarProducts.service;


import com.ams.SimilarProducts.integration.SimilarProductsIntegration;
import com.ams.SimilarProducts.model.ProductDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SimilarProductsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimilarProductsService.class);
    @Autowired
    private final SimilarProductsIntegration similarProductsIntegration;

    public SimilarProductsService(SimilarProductsIntegration similarProductsIntegration
                          ) {
        this.similarProductsIntegration = similarProductsIntegration;
    }

    public Optional<List<ProductDetail>> getProducts(String productId){
        Optional<List<String>> similarProducts = similarProductsIntegration.getSimilarProducts(productId);

        if (similarProducts.isEmpty()){
            return Optional.empty();
        }

        ArrayList<ProductDetail> productResponses = new ArrayList<>();
        similarProducts.get()
                .forEach(p ->
                {
                    Optional<ProductDetail> productDetail = productDetail(p);
                    productDetail.ifPresent(productResponses::add);
                });

        if (CollectionUtils.isEmpty(productResponses)){
            return Optional.empty();
        }
        return  Optional.of(productResponses);
    }

    private Optional<ProductDetail> productDetail(String productId) {
        Optional<ProductDetail> productDetail = similarProductsIntegration.getProductDetail(productId);
        if (productDetail.isEmpty()){
            LOGGER.info("::::::::: Product Detail Not Found for productId {} :::::::::", productId);
            return Optional.empty();
        }
        return productDetail;
    }

}
