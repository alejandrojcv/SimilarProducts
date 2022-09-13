package com.ams.SimilarProducts.controller;


import com.ams.SimilarProducts.model.ProductDetail;
import com.ams.SimilarProducts.service.SimilarProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RequestMapping("product")
@RestController
public class SimilarProductsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimilarProductsController.class);

    @Autowired
    private SimilarProductsService service;

    @GetMapping(value = "/{productId}/similar")
    public ResponseEntity<List<ProductDetail>> SimilarProducts(@PathVariable @RequestParam String productId){
        Optional<List<ProductDetail>> productResponse = service.getProducts(productId);
        if (productResponse.isEmpty()){
            LOGGER.info(":::::: Similar Products Not Found for productId: {}::::::", productId);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity.ok().body(productResponse.get());
    }

}
