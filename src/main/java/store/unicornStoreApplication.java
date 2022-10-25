package store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import store.data.dto.CustomerRegistrationRequest;
import store.data.dto.LoginRequest;
import store.data.dto.LoginResponse;
import store.service.CustomerService;
import store.service.CustomerServiceImpl;
import store.service.ProductService;
import store.service.ProductServiceImpl;

import java.util.Scanner;

public class unicornStoreApplication {

    private static final CustomerService customerService = new CustomerServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws JsonProcessingException {
        prompt();
        int intent = getCustomerIntent();
        processCustomerRequest(intent);
    }

    private static void prompt() {

        System.out.println("""
                Welcome to unicorns store. How may we be of service, today?
                1. register
                2. login
                3. Exit                  
                """);
    }

    private static int getCustomerIntent() {
        return scanner.nextInt();
    }

    private static void processCustomerRequest(int intent) throws JsonProcessingException {
        switch (intent) {
            case 1:
                System.out.println("""
                        REGISTER PAGE
                        """);
               var registrationRequest= collectRegistrationDetails();
                var response = customerService.register(registrationRequest);
                String jsonResponse = objectMapper.writeValueAsString(response);
                System.out.println(jsonResponse);

            case 2:
                System.out.println();
                System.out.println("""
                        LOGIN PAGE
                        """);
                LoginRequest loginRequest = collectLoginCredentials();
                LoginResponse loginResponse = customerService.login(loginRequest);
                String jsonLoginResponse = objectMapper.writeValueAsString(loginResponse);
                System.out.println(jsonLoginResponse);
                break;
            case 3:
        }
    }

    private static LoginRequest collectLoginCredentials() {
        LoginRequest loginRequest = new LoginRequest();
        String customerEmail = collectEmailAddress();
        loginRequest.setEmail(customerEmail);

        String customerPassword = collectPassword();
        loginRequest.setPassword(customerPassword);
        return loginRequest;
    }

    private static String collectPassword() {
        System.out.println("Enter Password: ");
        return scanner.next();
    }

    private static String collectEmailAddress() {
        System.out.println("Enter Email: ");
        return scanner.next();
    }

    private static CustomerRegistrationRequest collectRegistrationDetails() {
        CustomerRegistrationRequest registrationRequest = new CustomerRegistrationRequest();
        String customerEmail = collectEmailAddress();
        registrationRequest.setEmail(customerEmail);
        String customerPassword = collectPassword();
        registrationRequest.setPassword(customerPassword);
        System.out.println("Enter PhoneNumber");
        registrationRequest.setPhoneNUmber(scanner.next());
        scanner.nextLine();
        System.out.println("Enter Address: ");
        registrationRequest.setAddress(scanner.nextLine());

        return registrationRequest;

    }
}
