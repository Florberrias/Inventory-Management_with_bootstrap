package sg.edu.iss.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.edu.iss.model.Product;
import sg.edu.iss.model.ProductStatus;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.name=:name")
	public Product findbyName(@Param("name")String name);
	
	@Query("SELECT p FROM Product p WHERE p.supplier.companyName=:name")
	public Product findbySupplierCompanyName(@Param("name")String name);
	
	@Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.description, ' ', "
			+ "p.type, ' ', p.category, ' ', p.subCategory, ' ', p.supplier.companyName) LIKE %?1% AND p.status = 0")
	public List<Product> search(String keyword);
	
}
