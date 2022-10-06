package store.data.respositories;

import store.data.models.Vendor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VendorRepositoryImpl implements VendorRepository{
    List<Vendor> vendors = new ArrayList<>();

    @Override
    public Vendor save(Vendor vendor) {
    int newId =    generateId();
    vendor.setId(newId);
    vendors.add(vendor);
        return vendor;
    }
    @Override
    public Vendor findById(int id) {
        for(Vendor vendor: vendors){
            if(vendor.getId() == id)return vendor;
        }
        throw new vendorException("Vendor not found");
    }
    @Override
    public List<Vendor> findAll() {
        return vendors;
    }
    @Override
    public void delete(Vendor vendor) {
        vendors.remove(vendor);
    }

    @Override
    public void deleteAll() {
        vendors.clear();
    }

    private int generateId(){
      int vendorID =  vendors.size();
      return vendorID +1;
    }
}
