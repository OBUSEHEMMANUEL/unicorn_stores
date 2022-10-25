package store.service;

import lombok.AllArgsConstructor;
import store.data.dto.*;
import store.data.models.Product;
import store.exception.BuyerRegistrationException;
import store.data.models.Customer;
import store.data.respositories.CustomerRepository;
import store.exception.StoreException;
import store.utils.validators.UserDetailsValidator;

import java.util.Set;
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ProductService productService;
    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest registrationRequest) {
        if (!UserDetailsValidator.isValidEmailAddress(registrationRequest.getEmail()))
            throw new BuyerRegistrationException(String.format("email %s is invalid", registrationRequest.getEmail()));
        if (!UserDetailsValidator.isValidPhoneNumber(registrationRequest.getPhoneNUmber()))
            throw new BuyerRegistrationException(String.format("Invalid phone number",registrationRequest.getPhoneNUmber()));
        if (!UserDetailsValidator.isValidPassWord(registrationRequest.getPassword()))
            throw new BuyerRegistrationException(String.format("password %s is weak", registrationRequest.getPassword()));
        Customer customer = buildBuyer(registrationRequest);
        //save
        Customer savedCustomer = customerRepository.save(customer);

        //create registration response object

        CustomerRegistrationResponse response = buildBuyerRegistrationResponse(savedCustomer);
        return response;

    }
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        var foundCustomer = customerRepository.findByByEmail(loginRequest.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        if (foundCustomer.getPassword().equals(loginRequest.getPassword())) {
            loginResponse.setMessage("Successful login");
            return loginResponse;
        }
        loginResponse.setMessage("Authentication failed");
        return loginResponse;
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
        Customer customer = customerRepository.findById(productPurchaseRequest.getCustomerId());
        //search for product
//        System.out.println("purchase req prodId--> "+ productPurchaseRequest.getProductId());
        Product product = productService.getProductById(productPurchaseRequest.getProductId());
//        System.out.println("prodd---> "+product);
        if (product == null) throw new StoreException("Product not found");
        //validate quantity
        if (product.getQuantity() >= productPurchaseRequest.getQuantity()) {
            customer.getOrders().add(product);
            product.setQuantity(product.getQuantity()-productPurchaseRequest.getQuantity());
            productService.save(product);
            customerRepository.save(customer);
            return "order successful";
        } else {
            throw new StoreException("ordered quantity larger than available quantity");
        }
    }
}