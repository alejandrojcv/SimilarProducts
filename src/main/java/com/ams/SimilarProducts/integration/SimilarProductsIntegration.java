package com.ams.SimilarProducts.integration;

import com.ams.SimilarProducts.configuration.SimilarProductsConfiguration;
import com.ams.SimilarProducts.model.ProductDetail;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class SimilarProductsIntegration {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimilarProductsIntegration.class);
    private static final String SIMILAR_IDS = "/similarids";
    private static final String PRODUCT = "/product/";

    @Autowired
    private final SimilarProductsConfiguration configurationApp;


    public SimilarProductsIntegration(SimilarProductsConfiguration configurationApp) {
        this.configurationApp = configurationApp;

    }

    @HystrixCommand(fallbackMethod = "integrationFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10")})


    public Optional<List<String>> getSimilarProducts(String productId) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity(productId,headers);
        String integrationUrl = configurationApp.getUrlProduct() + PRODUCT + productId + SIMILAR_IDS;
        LOGGER.info(":::::: INTEGRATION URL  {} ::::::", integrationUrl);
        List<String> response = new RestTemplate().exchange(
                integrationUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<String>>() {
                })
                .getBody();
        return Optional.ofNullable(response);
    }


    public Optional<ProductDetail> getProductDetail(String productId) throws HttpClientErrorException {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity(productId,headers);
        String integrationUrl = configurationApp.getUrlProduct() + PRODUCT + productId;
        LOGGER.info(":::::: INTEGRATION URL  {} ::::::", integrationUrl);
        ProductDetail response = new RestTemplate().exchange(
                integrationUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<ProductDetail>() {
                })
                .getBody();
        return Optional.ofNullable(response);
    }
}
