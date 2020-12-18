package sg.edu.iss.controller;

import java.util.List;
import sg.edu.iss.model.ProductStatus;
import javax.validation.Valid;
import sg.edu.iss.repo.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.model.Product;
import sg.edu.iss.model.Supplier;
import sg.edu.iss.model.SupplierStatus;
import sg.edu.iss.service.ProductService;
import sg.edu.iss.service.ProductServiceImpl;
import sg.edu.iss.service.SupplierService;
import sg.edu.iss.service.SupplierServiceImpl;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService proservice;
	
	@Autowired
	private SupplierService supservice;
	
	@Autowired
	private StockRepository srepo;
    
	@Autowired
	public void setProductService(ProductServiceImpl productServiceImpl) {
		this.proservice =productServiceImpl;
	}

	//to transfer some supplierIDs to thymeleaf for product creation
	@Autowired
	public void setSupplierService(SupplierServiceImpl supserviceimpl) {
		this.supservice =supserviceimpl;
	}
	
	
	
	@RequestMapping(value="/list")
	public String listProducts(Model model, @Param("keyword") String keyword)
	
	{
		List<Product> products = proservice.listAll(keyword);
		model.addAttribute("products", products);
		model.addAttribute("keyword", keyword);
		return "products";
	}
	

	@RequestMapping(value = "/add")
	public String addProduct(Model model)
	{
		model.addAttribute("product", new Product());
		model.addAttribute("suppliers",supservice.findSuppliersByStatus(SupplierStatus.SUPPLYING));
		return "productform";
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") @Valid Product product,BindingResult bindingResult,  Model model) {
	   
		if (bindingResult.hasErrors()) 
		{	
			model.addAttribute("suppliers",supservice.findSuppliersByStatus(SupplierStatus.SUPPLYING));
			return "productform";
		}
		else if(proservice.findProductbyName(product.getName()) !=null 
				&& proservice.findProductbyName(product.getName()).getStatus() == ProductStatus.IN_USE)
		{   
			model.addAttribute("suppliers",supservice.findSuppliersByStatus(SupplierStatus.SUPPLYING));
			model.addAttribute("duplicate", "The product name already exists");
			return "productform";
		}
		
		else {
		// to add supplier inside the product by the Id parsed from form 
		 product.setSupplier(supservice.findSupplierbyId(product.getSupplierId()));
		 //adding product into corresponding supplier
		 Supplier correspondingSupplier =supservice.findSupplierbyId(product.getSupplierId());
		 correspondingSupplier.addProduct(product);
		 product.setStatus(ProductStatus.IN_USE);
		 //to save
		 proservice.saveProduct(product);
		 return "forward:/product/list";
		}
	}
	
	

	@RequestMapping(value = "/edit/{id}")
	public String editProduct(@PathVariable("id") Integer id, Model model)
	{
		
		model.addAttribute("product", proservice.findProductbyId(id));
		model.addAttribute("suppliers",supservice.findAllSuppliers());
		return "productform1";
		
	}
	
	//for edited product
	@RequestMapping(value = "/save1", method = RequestMethod.POST)
	public String saveeditedProduct(@ModelAttribute("product") @Valid Product product,BindingResult bindingResult,  Model model) {

		Product prevProduct=proservice.findProductbyId(product.getProductId());
		Product newProduct=(Product) model.getAttribute("product");
		if (bindingResult.hasErrors()) 
		{	
			model.addAttribute("suppliers",supservice.findSuppliersByStatus(SupplierStatus.SUPPLYING));
			return "productform1";
		}
		else if(!prevProduct.getName().equalsIgnoreCase(newProduct.getName()))
		{   if(proservice.findProductbyName(product.getName()) !=null 
		&& proservice.findProductbyName(product.getName()).getStatus() == ProductStatus.IN_USE)
			
			{   model.addAttribute("suppliers",supservice.findSuppliersByStatus(SupplierStatus.SUPPLYING));
				model.addAttribute("duplicate", "The product name already exists");
				return "productform1";}
			else {
			// to add supplier inside the product by the Id parsed from form 
			 product.setSupplier(supservice.findSupplierbyId(product.getSupplierId()));
			 //adding product into corresponding supplier
			 Supplier correspondingSupplier =supservice.findSupplierbyId(product.getSupplierId());
			 correspondingSupplier.addProduct(product);
			 //to save
			 proservice.saveProduct(product);
			 return "forward:/product/list";
			} 
		}
		
		else {
		// to add supplier inside the product by the Id parsed from form 
		 product.setSupplier(supservice.findSupplierbyId(product.getSupplierId()));
		 //adding product into corresponding supplier
		 Supplier correspondingSupplier =supservice.findSupplierbyId(product.getSupplierId());
		 correspondingSupplier.addProduct(product);
		 
		 //to save
		 proservice.saveProduct(product);
		 return "forward:/product/list";
		}
	}

	@RequestMapping(value = "/delete/{id}")
 	public String deleteProduct(@PathVariable("id") Integer id) 
	{
		if(proservice.findProductbyId(id).getStock()==null ||  
				(proservice.findProductbyId(id).getStock().getUnits()==0 && 
				proservice.findProductbyId(id).getConsumptions().isEmpty() && 
				proservice.findProductbyId(id).getReorders().isEmpty()))
		{	
			proservice.deleteProduct(proservice.findProductbyId(id));
			return "forward:/product/list";
		}
		if(proservice.findProductbyId(id).getStock().getUnits()!=0) {
			return "producterror_stockinhouse";
		}
		else {
			proservice.findProductbyId(id).setStatus(ProductStatus.ARCHIVED);
			List<Product> products = proservice.findProductbyId(id).getSupplier().getProducts();
			for(Product product : products) {
				if (product.equals(proservice.findProductbyId(id))) {
					product.setStatus(ProductStatus.ARCHIVED);
				}
			}
			srepo.deleteById(proservice.findProductbyId(id).getStock().getId());
			return "producterror_archived";
		}
	
	}
	
}
