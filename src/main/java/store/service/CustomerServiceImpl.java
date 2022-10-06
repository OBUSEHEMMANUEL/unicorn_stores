package store.service;

import store.data.dto.CustomerRegistrationRequest;
import store.data.dto.CustomerRegistrationResponse;
import store.data.dto.ProductPurchaseRequest;
import store.exception.BuyerRegistrationException;
import store.data.models.Customer;
import store.data.respositories.CustomerRepository;
import store.data.respositories.CustomerRepositoryImpl;
import store.utils.validators.UserDetailsValidator;

import java.util.Set;

public class CustomerServiceImpl implements CustomerService {
   private final CustomerRepository customerRepository = new CustomerRepositoryImpl();
   private  final  ProductService productService = new ProductServiceImpl();
    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest) {
        if (!UserDetailsValidator.isValidEmailAddress(registrationRequest.getEmail())) throw new BuyerRegistrationException(String.format("email %s is invalid", registrationRequest.getEmail()));
        if (!UserDetailsValidator.isValidPhoneNumber(registrationRequest.getPhoneNUmber())) throw new BuyerRegistrationException(String.format("Invalid phone number"));
        if(!UserDetailsValidator.isValidPassWord(registrationRequest.getPassword())) throw new BuyerRegistrationException(String.format("password %s is weak",registrationRequest.getPassword()));
        Customer customer = buildBuyer(registrationRequest);
        //save
       Customer savedCustomer = customerRepository.save(customer);

       //create registration response object

        CustomerRegistrationResponse response = buildBuyerRegistrationResponse(savedCustomer);
        return response;
    }



    private CustomerRegistrationResponse buildBuyerRegistrationResponse(Customer savedCustomer) {
        CustomerRegistrationResponse response = new CustomerRegistrationResponse();
        response.setMessage("User registration successful");
        response.setStatusCode(201);
        response.setUserId(savedCustomer.getId());
        return response;
    }

    private Customer buildBuyer(CustomerRegistrationRequest registrationRequest) {
        Customer customer = new Customer();
        customer.setEmail(registrationRequest.getEmail());
        customer.setPassword(registrationRequest.getPassword());
        Set<String> buyersAddressList = customer.getDeliveryAddress();
        buyersAddressList.add(registrationRequest.getAddress());
        customer.setPhoneNumber(registrationRequest.getPhoneNUmber());
        return customer;
    }

    @Override
    public String orderProduct(ProductPurchaseRequest productPurchaseRequest) {
        //search for product
        //validate quantity
        //

        return null;
    }
}
