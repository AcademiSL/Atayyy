package com.example.Atayyy;

import com.example.Atayyy.Product;
import com.example.Atayyy.ProductRepository;
import com.example.Atayyy.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;




@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ProductServiceIntegrationTest {

    @BeforeEach
    public void clearDatabase() {
        productRepository.deleteAll();
    }


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    @Rollback(value = false)
    public void testAddAndGetAllProducts() {

        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(1200.0);

        productRepository.save(product);

        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Laptop", products.get(0).getName());
        assertEquals(1200.0, products.get(0).getPrice());
    }
}
