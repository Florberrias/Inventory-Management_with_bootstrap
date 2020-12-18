package sg.edu.iss.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.model.Product;
import sg.edu.iss.model.ProductStatus;
import sg.edu.iss.model.Supplier;
import sg.edu.iss.model.SupplierStatus;
import sg.edu.iss.service.ProductService;
import sg.edu.iss.service.ProductServiceImpl;
import sg.edu.iss.service.SupplierService;
import sg.edu.iss.service.SupplierServiceImpl;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supservice;
	
	@Autowired
	public void setSupplierService(SupplierServiceImpl supserviceimpl) {
		this.supservice =supserviceimpl;
	}
	
	
	@RequestMapping(value="/list")
	public String listSuppliers(Model model)
	
	{
		model.addAttribute("suppliers",supservice.findSuppliersByStatus(SupplierStatus.SUPPLYING));
		return "suppliers";
	}
	

	@RequestMapping(value = "/add")
	public String addSupplier(Model model)
	{
		model.addAttribute("supplier", new Supplier());
		return "supplierform";
	}
	
	
	//for new supplier
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveSupplier(@ModelAttribute("supplier") @Valid Supplier supplier,BindingResult bindingResult,  Model model) 
	{
		if (bindingResult.hasErrors()) 
		{
			return "supplierform";
		}
		
		else if (supservice.findSupplierbyAddress(supplier.getAddress())!=null && 
				supservice.findSupplierbyAddress(supplier.getAddress()).getStatus() == SupplierStatus.SUPPLYING||
				supservice.findSupplierbyBrandName(supplier.getBrandName())!=null && 
				supservice.findSupplierbyBrandName(supplier.getBrandName()).getStatus() == SupplierStatus.SUPPLYING||
				supservice.findSupplierbyCompanyName(supplier.getCompanyName())!=null && 
				supservice.findSupplierbyCompanyName(supplier.getCompanyName()).getStatus() == SupplierStatus.SUPPLYING||
				supservice.findSupplierbyContactNumber(supplier.getContactNumber())!=null && 
				supservice.findSupplierbyContactNumber(supplier.getContactNumber()).getStatus() == SupplierStatus.SUPPLYING||
				supservice.findSupplierbyEmailAddress(supplier.getEmailAddress())!=null && 
				supservice.findSupplierbyEmailAddress(supplier.getEmailAddress()).getStatus() == SupplierStatus.SUPPLYING) 
		{
			model.addAttribute("duplicate", "There's already an existing data");
			return "supplierform";
		}
		else 
		{
			supplier.setStatus(SupplierStatus.SUPPLYING);
			supservice.saveSupplier(supplier);
		
			return "forward:/supplier/list";
		}
	}
	

	@RequestMapping(value = "/edit/{id}")
	public String editSupplier(@PathVariable("id") Integer id, Model model)
	{
		
		model.addAttribute("supplier", supservice.findSupplierbyId(id));
		return "supplierform1";
		
	}

	//for existing supplier
		@RequestMapping(value = "/save1", method = RequestMethod.POST)
		public String saveeditedSupplier(@ModelAttribute("supplier") @Valid Supplier supplier,BindingResult bindingResult,  Model model) 
		{
			Supplier prevSupplier=supservice.findSupplierbyId(supplier.getSupplierId());
			Supplier newSupplier=(Supplier)model.getAttribute("supplier");
			if (bindingResult.hasErrors()) 
			{   
				return "supplierform1";
			}
			if(!prevSupplier.getAddress().equalsIgnoreCase(newSupplier.getAddress()))
			{
				if(supservice.findSupplierbyAddress(supplier.getAddress())!=null && supservice.findSupplierbyAddress(supplier.getAddress()).getStatus() == SupplierStatus.SUPPLYING)
				{
					model.addAttribute("duplicate", "There's already an existing address");
					return "supplierform1";
				}
				else {
					supplier.setStatus(SupplierStatus.SUPPLYING);
					supservice.saveSupplier(supplier);
					
					return "forward:/supplier/list";
				}
			}
			if(!prevSupplier.getBrandName().equalsIgnoreCase(newSupplier.getBrandName()))
			{
				if(supservice.findSupplierbyBrandName(supplier.getBrandName())!=null && supservice.findSupplierbyBrandName(supplier.getBrandName()).getStatus() == SupplierStatus.SUPPLYING )
				{
					model.addAttribute("duplicate", "There's already an existing brandname");
					return "supplierform1";
				}
				else {
					supplier.setStatus(SupplierStatus.SUPPLYING);
					supservice.saveSupplier(supplier);
					
					return "forward:/supplier/list";
				}
			}
			if(!prevSupplier.getCompanyName().equalsIgnoreCase(newSupplier.getCompanyName()))
			{
				if(supservice.findSupplierbyCompanyName(supplier.getCompanyName())!=null && supservice.findSupplierbyCompanyName(supplier.getCompanyName()).getStatus() == SupplierStatus.SUPPLYING)
				{
					model.addAttribute("duplicate", "There's already an existing companyname");
					return "supplierform1";
				}
				else {
					supplier.setStatus(SupplierStatus.SUPPLYING);
					supservice.saveSupplier(supplier);
					
					return "forward:/supplier/list";
				}
			}
			if(!prevSupplier.getContactNumber().equalsIgnoreCase(newSupplier.getContactNumber()))
			{
				if(supservice.findSupplierbyContactNumber(supplier.getContactNumber())!=null && supservice.findSupplierbyContactNumber(supplier.getContactNumber()).getStatus()==SupplierStatus.SUPPLYING)
				{
					model.addAttribute("duplicate", "There's already an existing contact number");
					return "supplierform1";
				}
				else {
					supplier.setStatus(SupplierStatus.SUPPLYING);
					supservice.saveSupplier(supplier);
					
					return "forward:/supplier/list";
				}
			}
			if(!prevSupplier.getEmailAddress().equalsIgnoreCase(newSupplier.getEmailAddress()))
			{
				if(supservice.findSupplierbyEmailAddress(supplier.getEmailAddress())!=null && supservice.findSupplierbyEmailAddress(supplier.getEmailAddress()).getStatus()==SupplierStatus.SUPPLYING)
				{
					model.addAttribute("duplicate", "There's already an existing email address");
					return "supplierform1";
				}
				else {
					supplier.setStatus(SupplierStatus.SUPPLYING);
					supservice.saveSupplier(supplier);
					
					return "forward:/supplier/list";
				}
				
			}
			
		
			else 
			{
		    supplier.setStatus(SupplierStatus.SUPPLYING);
			supservice.saveSupplier(supplier);
			
			return "forward:/supplier/list";
			}
		}

	@RequestMapping(value = "/delete/{id}")
 
	public String deleteSupplier(@PathVariable("id") Integer id) {
	
		//if corresponding supplier is found to be connected to product
		List<Product> products = supservice.findSupplierbyId(id).getProducts();
		for(Product product : products) {
			if(product.getStatus() == ProductStatus.IN_USE) {
				break;
			}
			supservice.findSupplierbyId(id).setStatus(SupplierStatus.NOT_SUPPLYING);
		}
	
		if(products.isEmpty())
		{
			supservice.deleteSupplier(supservice.findSupplierbyId(id));
			return "forward:/supplier/list";
		}
		else if(supservice.findSupplierbyId(id).getStatus() == SupplierStatus.NOT_SUPPLYING) 
		{
			return "forward:/supplier/list";
		}
		else 
		{
			return "suppliererror";
		}
		
		}
	
}
