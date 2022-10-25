package store.data.respositories;

import store.data.models.Product;
import store.data.models.Vendor;

import java.util.List;

public interface VendorRepository {
    Vendor save(Vendor vendor);
    Vendor findById(int id);
    List< Vendor> findAll();
    void delete (Vendor vendor);

    void deleteAll();

}
