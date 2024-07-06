package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product(1L, "Product1", "Description1", 10.0);
        Product product2 = new Product(2L, "Product2", "Description2", 20.0);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        assertEquals("Product1", products.get(0).getName());
        assertEquals("Product2", products.get(1).getName());
    }

    @Test
    void testSaveProduct() {
        Product product = new Product(1L, "Product1", "Description1", 10.0);

        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);

        assertEquals("Product1", savedProduct.getName());
        assertEquals("Description1", savedProduct.getDescription());
        assertEquals(10.0, savedProduct.getPrice());
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;

        productService.deleteProduct(productId);

        verify(productRepository).deleteById(productId);
    }
}
