package store.data.respositories;


import org.springframework.data.jpa.repository.JpaRepository;
import store.data.models.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository {
    Product save(Product product);
    Product findById(int id);
    List<Product> findAll();
    void delete (Product product);

    void deleteAll();
}
