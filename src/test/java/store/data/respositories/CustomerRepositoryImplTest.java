package store.data.respositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.models.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryImplTest {
    private static final CustomerRepository CUSTOMER_REPOSITORY = new CustomerRepositoryImpl();
    Customer customer;
    Customer secondCustomer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setFirstName("Godman");
        customer.setLastName("Buhari");
        customer.setEmail("emilokan2022gmail.com");
        customer.setPassword("IloveNaija");


        secondCustomer = new Customer();
        customer.setFirstName("jennifer");
        customer.setLastName("Emilokan");
        customer.setEmail("jennifer123@gmail.com");
        customer.setPassword("Winner");
    }
    @AfterEach
    void tearDown(){
        CUSTOMER_REPOSITORY.deleteAll();
    }
    @Test
    void saveTest(){
        //before save
      assertEquals(0, customer.getId());
      assertEquals(0, CUSTOMER_REPOSITORY.findAll().size());
      //save buyer
        Customer savedCustomer = CUSTOMER_REPOSITORY.save(customer);
        //buyer has id
        assertEquals(1, savedCustomer.getId());
        //there is just one buyer
        var buyerList = CUSTOMER_REPOSITORY.findAll();
        assertEquals(1,buyerList.size());

        //save second buyer
        Customer savedSecondCustomer = CUSTOMER_REPOSITORY.save(secondCustomer);
        //second buyerID
        assertEquals(2, savedSecondCustomer.getId());

        //there is second buyer
        assertEquals(2,buyerList.size());

    }


    @Test
    void findByIdTest() {
        Customer firstSavedCustomer = CUSTOMER_REPOSITORY.save(customer);
        Customer secondSavedCustomer = CUSTOMER_REPOSITORY.save(secondCustomer);

        Customer foundCustomer = CUSTOMER_REPOSITORY.findById(secondSavedCustomer.getId());
        assertEquals(secondSavedCustomer, foundCustomer);

    }

    @Test
    void findAllTest() {
        CUSTOMER_REPOSITORY.save(customer);
     var listOfAllBuyerInDb = CUSTOMER_REPOSITORY.findAll();
     assertEquals(1,listOfAllBuyerInDb.size());

    }

    @Test
    void deleteTest() {
        CUSTOMER_REPOSITORY.save(customer);
        var listOfAllBuyerInDb = CUSTOMER_REPOSITORY.findAll();
        assertEquals(1,listOfAllBuyerInDb.size());
        CUSTOMER_REPOSITORY.save(secondCustomer);
      //  var listOfAllBuyerInDb = buyerRepository.findAll();
        assertEquals(2,listOfAllBuyerInDb.size());
        CUSTOMER_REPOSITORY.delete(secondCustomer);
        assertEquals(1,listOfAllBuyerInDb.size());

    }
    @Test
    void deleteAllTest(){
        CUSTOMER_REPOSITORY.save(customer);
        assertEquals(1, CUSTOMER_REPOSITORY.findAll().size());
        CUSTOMER_REPOSITORY.deleteAll();
        assertEquals(0, CUSTOMER_REPOSITORY.findAll().size());
    }
}