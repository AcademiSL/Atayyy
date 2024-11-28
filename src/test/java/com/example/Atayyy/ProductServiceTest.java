package com.example.Atayyy;

import com.example.Atayyy.Service.ProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
//@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class ProductServiceTest {
    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductService productService = new ProductService(productRepository);

    @Test
    @Rollback(value = false)

    public void testGetAllProducts() {
        Product product = new Product();
        product.setName("Phone");
        product.setPrice(800.0);
        System.out.println(product.getName());

        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        List<Product> products = productService.getAllProducts();
        System.out.println(product.getPrice());
        assertEquals(1, products.size());
        assertEquals("Phone", products.get(0).getName());
        assertEquals(800.0, products.get(0).getPrice());
        verify(productRepository, times(1)).findAll();
    }
}
