package sg.edu.iss.service;

import java.util.ArrayList;
import java.util.List;

import sg.edu.iss.model.Product;
import sg.edu.iss.model.ProductStatus;

public interface ProductService {
	 public boolean saveProduct(Product product);
	 public ArrayList<Product> findAllProducts();
	 public void deleteProduct(Product product);
	 public Product findProductbyId(Integer id);
	 public boolean findSupplierfromProduct(Integer supplierId);
	 public Product findProductbyName(String name);
	 public Product findProductbySupplierCompanyName(String name);
	 public ArrayList<Product> findProductsByStatus(ProductStatus status);
	 public List<Product> listAll(String keyword);
}
