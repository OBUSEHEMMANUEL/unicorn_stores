package store.data.respositories;

import store.exception.storeException;
import store.data.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {


    private final List<Customer> customers = new ArrayList<>();
    @Override
    public Customer save(Customer customer) {
      int newId =  generateId();
      customer.setId(newId);
     customers.add(customer);
        return customer;
    }

    @Override
    public Customer findById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) return customer;

        }
        throw new storeException("ID NOT FOUND");
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public void delete(Customer customer) {
     customers.remove(customer);
    }

    @Override
    public void deleteAll() {
        customers.clear();
    }

    private int generateId(){
        int numberOfBuyerInDb = customers.size();
        return numberOfBuyerInDb+1;

    }

}
