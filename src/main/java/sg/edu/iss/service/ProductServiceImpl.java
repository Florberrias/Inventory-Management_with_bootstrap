package sg.edu.iss.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.iss.model.ProductStatus;
import sg.edu.iss.model.Product;
import sg.edu.iss.repo.ProductRepository;
import sg.edu.iss.repo.SupplierRepository;


@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository prorepo;
	@Autowired
    SupplierRepository suprepo; //to find the supplier related to product
	@Override
	@Transactional
	public boolean saveProduct(Product product) {
		if(prorepo.save(product)!=null) 
		{ 
			return true; }
		return false;
	}

	@Override
	public ArrayList<Product> findAllProducts() {
		return (ArrayList<Product>)prorepo.findAll();
		
	}

	@Override
	@Transactional
	public void deleteProduct(Product product) {
		prorepo.delete(product);
		
	}

	@Override
	public Product findProductbyId(Integer id) {
		return prorepo.findById(id).get();
	
	}

	@Override
	public boolean findSupplierfromProduct(Integer supplierId) {
		
		boolean toreturn=false;
		
		List<Product> products= prorepo.findAll();
		for (Product p: products) 
		{
			if(p.getSupplierId()==supplierId)
				{
					toreturn=true;
				}
				
		}
	    return toreturn;
		
	}

	@Override
	public Product findProductbyName(String name) {
		return prorepo.findbyName(name);
	}

	@Override
	public Product findProductbySupplierCompanyName(String name) {
		return prorepo.findbySupplierCompanyName(name);
	}

	@Override
	public ArrayList<Product> findProductsByStatus(ProductStatus status) {
		ArrayList<Product> products = findAllProducts();
		ArrayList<Product> productsByStatus = new ArrayList<Product>();
		for(Product product : products) {
			if(product.getStatus() == status) {
				productsByStatus.add(product);
				}
		}

		return productsByStatus;
	}

	@Override
	public List<Product> listAll(String keyword){
		if (keyword != null) {
			return (List<Product>)prorepo.search(keyword);
		}
		return (List<Product>)findProductsByStatus(ProductStatus.IN_USE);
	}

}
