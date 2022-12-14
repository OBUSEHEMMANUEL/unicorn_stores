package store.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import store.data.dto.AddProductRequest;
import store.data.dto.AddProductResponse;
import store.data.models.Category;
import store.data.models.Product;
import store.data.respositories.ProductRepository;

import java.math.BigDecimal;
@
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public AddProductResponse addProduct(AddProductRequest addProductRequest) {
        Product savedProduct = buildProduct(addProductRequest);
        AddProductResponse response = buildProductRegistrationResponse(savedProduct);
        return response;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
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
        System.out.println("here---> "+savedProduct.getId());
        response.setProductId(savedProduct.getId());
        response.setStatusCode(201);
        return response;
    }
}
