package store.data.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
public class Customer extends User{
    private String firstName;
    private String LastName;
    private Set<String> deliveryAddress = new TreeSet<>();
    private List<Product> orders = new ArrayList<>();


}
