package sg.edu.iss.controller;

import java.time.LocalDate;  
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

import sg.edu.iss.model.Reorder;
import sg.edu.iss.service.ReorderImplementation;
import sg.edu.iss.service.ReorderInterface;

@Controller
@RequestMapping("/reorders")
public class ReorderController {

	@Autowired
	private ReorderInterface rservice;

	@Autowired
	public void setReorderImplementation(ReorderImplementation rimpl) {
		this.rservice = rimpl;
	}
	
	// User requests to view the list of orders made
	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<Reorder> rlist = rservice.list();
		model.addAttribute("rlist", rlist);
		model.addAttribute("today", LocalDate.now().toString());
		return "reorderslist";
	}
	
//	// User requests to view order details of a specific order made
//	@RequestMapping(value = "/reorderreport", method = RequestMethod.GET)
//	public String reorderReport(Model model) {
//		Reorder reorder = new Reorder();
//		model.addAttribute("reorder", reorder);
//		return "reorderreport";
//	}

	// show reorder form for specific record id
	@RequestMapping(value = "/viewform/{id}", method = RequestMethod.GET)
	public String viewReorderForm(@PathVariable("id") Integer id, Model model) {
		Reorder reorder = rservice.getReorderById(id);
		model.addAttribute("reorder", reorder);
		return "reorderform";
	}

	// place order in reorder form
	@RequestMapping(value = "/placeorder/{id}", method = RequestMethod.POST)
	public String placeOrder(@PathVariable("id") Integer id, @Valid Reorder reorder, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "reorderform";
		}
		// validation check that when orderQty < MOQ
		else if (!rservice.isOrderable(reorder)) {
			model.addAttribute("belowMOQ", "Order quantity has to be equal or greater than MOQ");
			return "reorderform";
		}
		else {
			// update order quantity of reorder object
			rservice.updateOrderQty(reorder); 
			// update order status or reorder object 
			rservice.updateOrderStatus(reorder);
			// update 
			rservice.addDate(reorder);
			// return reorders list
			return "forward:/reorders/list";
		}
	}
	
	// show order for changing status to received for specific record id
	@RequestMapping(value = "/orderreceived/{id}", method = RequestMethod.POST)
	public String orderReceived(@PathVariable("id") Integer id, Reorder reorder, Model model) {
		// update order status or reorder object 
		rservice.updateOrderStatus(reorder);
		// update date received of reorder object 
		rservice.updateDateReceived(reorder);
		// return reorders list
		return "forward:/reorders/list";
	}

	// show order for specific record id
	@RequestMapping(value = "/vieworder/{id}", method = RequestMethod.GET)
	public String viewOrder(@PathVariable("id") Integer id, Model model) {
		Reorder reorder = rservice.getReorderById(id);
		model.addAttribute("reorder", reorder);
		return "vieworder";
	}

	// get stock entry form to view
	@RequestMapping(value = "/viewseform/{id}", method = RequestMethod.GET)
	public String viewSEForm(@PathVariable("id") Integer id, Model model) {
		Reorder reorder = rservice.getReorderById(id); 
		model.addAttribute("reorder", reorder);
		return "seformview"; 
	}

	// get stock entry form to edit
	@RequestMapping(value = "/editseform/{id}", method = RequestMethod.GET)
	public String editSEForm(@PathVariable("id") Integer id, Model model) {
		Reorder reorder = rservice.getReorderById(id); 
		model.addAttribute("reorder", reorder);
		return "seformedit"; 
	}
	
	// save stock entry form 
	@RequestMapping(value= "/processform/{id}", params="saveSEForm", method = RequestMethod.POST)
	public String saveSEForm(@PathVariable("id") Integer id, @ModelAttribute("reorder") @Valid Reorder reorder, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "seformedit";
		}
		else {
			// update damaged quantity of reorder object
			rservice.updateDamagedQty(reorder); 
			// update damaged description of reorder object
			rservice.updateDamagedDescription(reorder); 
			// return reorders list
			return "forward:/reorders/list";
		}
	}
	
	// add to inventory 
	@RequestMapping(value= "/processform/{id}", params="addToInventory", method = RequestMethod.POST)
	public String addToInventory(@PathVariable("id") Integer id, @ModelAttribute("reorder") @Valid Reorder reorder, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "seformedit";
		}
		else {
			// update damaged quantity of reorder object
			rservice.updateDamagedQty(reorder); 
			// update damaged description of reorder object
			rservice.updateDamagedDescription(reorder); 
			// update reorder object 
			rservice.updateOrderStatus(reorder);
			//update new stock
			rservice.updateStock(reorder);
			// return reorders list
			return "forward:/reorders/list";
		}
	}
	
//	// generate reorder report
//	@RequestMapping(value = "/reorderreport", method = RequestMethod.GET)
//	public String generateReorderReport(Model model) {
//		Reorder reorder = new Reorder();
//		model.addAttribute("reorder", reorder);
//		return "reorderreport";
//	}
//
}
	


