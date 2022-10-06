package store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.dto.AddProductRequest;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {
private final ProductService productService = new ProductServiceImpl();
private AddProductRequest addProductRequest;

    @BeforeEach
    void setUp() {
        addProductRequest = new AddProductRequest();
        addProductRequest.setName("Milo");
        addProductRequest.setPrice(20.00);
        addProductRequest.setCategory("beverages");
    }

    @Test
    void addProductTest() {
      var response =  productService.addProduct(addProductRequest);
      assertNotNull(response);
      assertNotNull(response.getMessage());
      assertEquals(1,response.getProductId());

    }
    @Test
    void getP
}