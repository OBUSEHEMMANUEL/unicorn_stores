package store.data.respositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.data.models.Vendor;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VendorRepositoryImplTest {
    private static final VendorRepository vendorRepository = new VendorRepositoryImpl();

    Vendor vendor;
    Vendor  secondVendor;

    @BeforeEach
    void setUp() {
        vendor = new Vendor();
        Set<String> storeAddress = new HashSet<>();
      storeAddress.add("Semicolon");
        vendor.setStoreName("Derek stores");
        vendor.setStoreAddress(storeAddress);
        vendor.setEmail("derek@email.com");
        vendor.setPassword("village");

        secondVendor = new Vendor();
        storeAddress.add("36 iwaya");
        secondVendor.setStoreName("shop with berry");
        secondVendor.setStoreAddress(storeAddress);
        secondVendor.setEmail("berryalan@gmail.com");
        secondVendor.setPassword("berry123");

    }

    @AfterEach
    void tearDown() {
        vendorRepository.deleteAll();
    }

    @Test
    void saveTest() {
        assertEquals(0,vendor.getId());
        vendorRepository.save(vendor);
        assertEquals(1,vendor.getId());
        assertEquals(1,vendorRepository.findAll().size());
        vendorRepository.save(secondVendor);
        assertEquals(2,secondVendor.getId());
        assertEquals(2,vendorRepository.findAll().size());
    }
    @Test
    void findByIdTest() {
        Vendor savedFirstVendor = vendorRepository.save(vendor);
        Vendor savedSecondVendor = vendorRepository.save(secondVendor);
        assertEquals(savedFirstVendor,vendorRepository.findById(vendor.getId()));
        assertEquals(savedSecondVendor,vendorRepository.findById(secondVendor.getId()));
    }

    @Test
    void findAllTest() {
        vendorRepository.save(vendor);
        vendorRepository.save(secondVendor);
        assertEquals(2,vendorRepository.findAll().size());
    }

    @Test
    void deleteTest() {
        vendorRepository.save(vendor);
        vendorRepository.save(secondVendor);
        assertEquals(2,vendorRepository.findAll().size());
        vendorRepository.delete(vendor);
        assertEquals(1,vendorRepository.findAll().size());
    }
    @Test
    void deleteAllTest(){
        vendorRepository.save(vendor);
        vendorRepository.save(secondVendor);
        assertEquals(2,vendorRepository.findAll().size());
        vendorRepository.deleteAll();
        assertEquals(0,vendorRepository.findAll().size());
    }

}