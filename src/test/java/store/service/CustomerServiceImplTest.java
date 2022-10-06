package store.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.dto.AddProductRequest;
import store.data.dto.CustomerRegistrationRequest;
import store.data.dto.ProductPurchaseRequest;
import store.exception.BuyerRegistrationException;


import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceImplTest {
    private final CustomerService customerService = new CustomerServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    private CustomerRegistrationRequest firstCustomerRegistrationRequest;
    private CustomerRegistrationRequest secondCustomerRegistrationRequest;
    private ProductPurchaseRequest productPurchaseRequest;
    private AddProductRequest addProductRequest;


    @BeforeEach
    void setUp() {

        firstCustomerRegistrationRequest = new CustomerRegistrationRequest();
        firstCustomerRegistrationRequest.setEmail("ademusa222@gmail.com");
        firstCustomerRegistrationRequest.setAddress("312 herbert marcauly way");
        firstCustomerRegistrationRequest.setPassword("ademusa22@");
        firstCustomerRegistrationRequest.setPhoneNUmber("09011122244");

        secondCustomerRegistrationRequest = new CustomerRegistrationRequest();
        secondCustomerRegistrationRequest.setEmail("chikodi22#gmail.com");
        secondCustomerRegistrationRequest.setAddress("Semicolon avenue");
        secondCustomerRegistrationRequest.setPassword("chikodi-22@");
        secondCustomerRegistrationRequest.setPhoneNUmber("09055523244");

        productPurchaseRequest = new ProductPurchaseRequest();
        productPurchaseRequest.setProductId(1);
        productPurchaseRequest.setQuantity(2);

        addProductRequest = new AddProductRequest();
        addProductRequest.setCategory("beverages");
        addProductRequest.setName("Milo");
        addProductRequest.setPrice(20.00);
    }

    @Test
    void register() {
     var response =   customerService.register(firstCustomerRegistrationRequest);
     assertNotNull(response);
     assertEquals(response.getStatusCode(), 201);
    }
    @Test
    void userWithInvalidDetailsGetsExceptionTest(){
        assertThrows(BuyerRegistrationException.class, ()-> customerService.register(secondCustomerRegistrationRequest));

    }

    @Test
    void orderProductTest() {
     var addProductResponse = productService.addProduct(addProductRequest);
     assertNotNull(addProductResponse);
     assertEquals(201,addProductResponse.getStatusCode());
      String response =  customerService.orderProduct(productPurchaseRequest);
      assertNotNull(response);

    }
}