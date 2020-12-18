package sg.edu.iss.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.model.Car;
import sg.edu.iss.model.Consumption;
import sg.edu.iss.model.Product;
import sg.edu.iss.model.ProductStatus;
import sg.edu.iss.model.Supplier;
import sg.edu.iss.model.SupplierStatus;
import sg.edu.iss.service.ConsumptionService;
import sg.edu.iss.service.ConsumptionServiceImpl;
import sg.edu.iss.service.ProductService;
import sg.edu.iss.service.ProductServiceImpl;

@Controller
@RequestMapping("/usage")
public class UsageController {

	@Autowired
	private ProductService proservice;
	
	@Autowired
	private ConsumptionService conservice;
	
	
	@Autowired
	public void setProductService(ProductServiceImpl productServiceImpl) {
		this.proservice =productServiceImpl;
	}

	
	@Autowired
	public void setConsumptionService(ConsumptionServiceImpl conServiceImpl)
	{
		this.conservice=conServiceImpl;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addtransaction(Model model) {
		Consumption consumption = new Consumption();
		List<Product> products = proservice.findAllProducts();
		List<Car>cars=conservice.findallCars();
		model.addAttribute("consumption",consumption);
		model.addAttribute("products",products);
		model.addAttribute("cars",cars);
		return "usageform";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUsage(@ModelAttribute("consumption") @Valid Consumption consumption,BindingResult bindingResult,  Model model) {
	    System.out.print(consumption.getTransaction().getDate());
	    System.out.print(consumption.getTransaction().getCar());
	    System.out.print(consumption.getProduct());
	    
		if (bindingResult.hasErrors()) 
		{	
			System.out.print(bindingResult.getAllErrors());
			List<Product> products = proservice.findAllProducts();
			List<Car>cars=conservice.findallCars();
			model.addAttribute("consumption",consumption);
			model.addAttribute("products",products);
			model.addAttribute("cars",cars);
		
			return "usageform";
		}
		else {
			
			conservice.saveConsumption(consumption);
			return "forward:/product/list";
		}
		
		 
		}
	}
	

