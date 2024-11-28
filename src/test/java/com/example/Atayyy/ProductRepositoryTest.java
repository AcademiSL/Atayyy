package com.example.Atayyy;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)


public class ProductRepositoryTest {


    @BeforeEach
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Autowired
    private ProductRepository productRepository;

   // @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)


    @Test
    @Rollback(value = false)
    @Transactional
    public void testFindByName() {
        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(1500.0);
      //  System.out.println(product.getName());

        productRepository.save(product);

        //System.out.println(product.getName());

        Product found = productRepository.findByName("Laptop");
        assertThat(found).isNotNull();
       // System.out.println(product.getPrice());
        assertThat(found.getPrice()).isEqualTo(1500.0);
    }

    @Test
    public void testAddition() {
        int result = 2 + 3;
        assertEquals(5, result);
    }
}
    


