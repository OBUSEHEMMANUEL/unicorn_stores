package store.service;

import store.data.dto.AddProductRequest;
import store.data.dto.AddProductResponse;
import store.data.models.Category;
import store.data.models.Product;
import store.data.respositories.ProductRepository;
import store.data.respositories.ProductRepositoryImpl;

import java.math.BigDecimal;

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository = new ProductRepositoryImpl();
    @Override
    public AddProductResponse addProduct(AddProductRequest addProductRequest) {
        Product savedProduct = buildProduct(addProductRequest);

        AddProductResponse response = buildProductRegistrationResponse(savedProduct);
        return response;
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    private Product buildProduct(AddProductRequest addProductRequest) {
        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(addProductRequest.getPrice()));
        product.setCategory(Category.valueOf(addProductRequest.getCategory().toUpperCase()));
        product.setName(addProductRequest.getName());
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    private AddProductResponse buildProductRegistrationResponse(Product savedProduct) {
        AddProductResponse response = new AddProductResponse();
        response.setMessage("product created");
        response.setProductId(savedProduct.getId());
        response.setStatusCode(201);
        return response;
    }
}
