package store.data.respositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.models.Category;
import store.data.models.Product;
import store.exception.ProductNotException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryImplTest {
    private  static final ProductRepository productRepository = new ProductRepositoryImpl();
    Product product;
    Product secondProduct;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setName("Milk");
        product.setPrice(BigDecimal.valueOf(500));
        product.setCategory(Category.BEVERAGES);


        secondProduct = new Product();
        secondProduct.setName("Iron");
        secondProduct.setPrice(BigDecimal.valueOf(1000));
        secondProduct.setCategory(Category.APPLIANCES);
    }
    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void saveTest() {
        productRepository.save(product);
        assertEquals(1,product.getId());
        assertEquals(1,productRepository.findAll().size());
        productRepository.save(secondProduct);
        assertEquals(2,productRepository.findAll().size());
    }

    @Test
    void findProductByIdTest() {
      var savedId =  productRepository.save(product);
        productRepository.save(secondProduct);
        assertEquals(product,productRepository.findById(product.getId()));


        var foundProduct = productRepository.findById(savedId.getId());
        assertEquals(savedId.getId(),foundProduct.getId());
    }

    @Test
    void testThatExceptionIsThrown(){
        productRepository.save(product);
        assertThrows(ProductNotException.class, ()->productRepository.findById(900));
    }

    @Test
    void findAllProductTest() {
        productRepository.save(product);
        assertEquals(1,productRepository.findAll().size());
        productRepository.save(secondProduct);
        assertEquals(2,productRepository.findAll().size());
    }

    @Test
    void deleteTest() {
        productRepository.save(product);
        assertEquals(1,productRepository.findAll().size());
        productRepository.save(secondProduct);
        assertEquals(2,productRepository.findAll().size());
        productRepository.delete(product);
        assertEquals(1,productRepository.findAll().size());
    }

    @Test
    void deleteAllTest(){
        productRepository.save(product);
        assertEquals(1,productRepository.findAll().size());
        productRepository.save(secondProduct);
        assertEquals(2,productRepository.findAll().size());
        productRepository.deleteAll();
        assertEquals(0,productRepository.findAll().size());

    }
    @Test
    void categoryTest(){
        productRepository.save(product);
        assertEquals(1,productRepository.findAll().size());
        assertEquals(Category.BEVERAGES,product.getCategory());
        productRepository.save(secondProduct);
        assertEquals(2,productRepository.findAll().size());
        assertEquals(Category.APPLIANCES,secondProduct.getCategory());

    }
}