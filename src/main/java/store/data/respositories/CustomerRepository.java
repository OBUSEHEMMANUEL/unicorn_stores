package store.data.respositories;

import store.data.models.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);
    Customer findById(int id);
    List <Customer> findAll();

    Customer findByByEmail(String email);
    void delete (Customer customer);
    void deleteAll();

}
