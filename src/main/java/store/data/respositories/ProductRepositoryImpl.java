package store.data.respositories;

import store.exception.ProductNotException;
import store.data.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository{
    List<Product> products = new ArrayList<>();

    @Override
    public Product save(Product product) {
      var numberId =  numberOfProduct();
        product.setId(numberId);
       products.add(product);
       return product;
    }
    @Override
    public Product findById(int id) {
        for (Product product :products) {
            if (product.getId() == id) return product;
        }
        throw new ProductNotException("product not found");
    }
    @Override
    public List<Product> findAll() {
        return products;
    }
    @Override
    public void delete(Product product) {
        products.remove(product);
    }

    @Override
    public void deleteAll() {
        products.clear();
    }

    private int numberOfProduct(){
        int numberOfProduct = products.size();
        return numberOfProduct +1;
    }
}
