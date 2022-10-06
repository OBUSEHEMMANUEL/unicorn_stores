package store.service;

import store.data.dto.AddProductRequest;
import store.data.dto.AddProductResponse;
import store.data.models.Product;


public interface ProductService {
    AddProductResponse addProduct(AddProductRequest addProduct);

    Product getProductById(int id);

}
