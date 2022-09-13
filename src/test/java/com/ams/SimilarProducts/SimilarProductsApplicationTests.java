package com.ams.SimilarProducts;

import com.ams.SimilarProducts.integration.SimilarProductsIntegration;
import com.ams.SimilarProducts.model.ProductDetail;
import com.ams.SimilarProducts.service.SimilarProductsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimilarProductsApplicationTests {

	@Mock
	private SimilarProductsIntegration integration;

	@InjectMocks
	private SimilarProductsService service;

	private static final String productId = "2348";
	private static final List<String> similarProducts = Arrays.asList("2348", "5012", "9312");
	private static final ProductDetail productDetail = ProductDetail
			.builder()
			.availability(true)
			.id("2348")
			.price(5083.23)
			.name("Samsung Galaxy A33")
			.build();
	@Test
	public void similarProductsNotFound(){
		when(integration.getSimilarProducts(Mockito.any(String.class))).thenReturn(Optional.empty());
		Optional<List<ProductDetail>> response = service.getProducts(productId);
		Assertions.assertEquals(response,Optional.empty());
	}

	@Test
	public void productDetailNotFound(){
		when(integration.getSimilarProducts(Mockito.any(String.class))).thenReturn(Optional.of(similarProducts));
		when(integration.getProductDetail(Mockito.any(String.class))).thenReturn(Optional.empty());
		Optional<List<ProductDetail>> response = service.getProducts(productId);
		Assertions.assertEquals(response,Optional.empty());
	}

	@Test
	public void productDetailFound(){
		when(integration.getSimilarProducts(Mockito.any(String.class))).thenReturn(Optional.of(similarProducts));
		when(integration.getProductDetail(Mockito.any(String.class))).thenReturn(Optional.of(productDetail));
		Optional<List<ProductDetail>> response = service.getProducts(productId);
		Assertions.assertNotEquals(response,Optional.empty());
	}



}
