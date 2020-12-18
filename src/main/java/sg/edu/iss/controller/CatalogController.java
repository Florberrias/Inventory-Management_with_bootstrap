package sg.edu.iss.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.model.Consumption;
import sg.edu.iss.model.Product;
import sg.edu.iss.model.Transaction;
import sg.edu.iss.repo.StockRepository;
import sg.edu.iss.service.ConsumptionService;
import sg.edu.iss.service.ConsumptionServiceImpl;
import sg.edu.iss.service.ProductService;
import sg.edu.iss.service.ProductServiceImpl;
import sg.edu.iss.service.TransactionService;
import sg.edu.iss.service.TransactionServiceImpl;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

	@Autowired
	private ProductService proservice;
	
	@Autowired
	private StockRepository storepo;
	
	@Autowired
	private ConsumptionService conservice;

	

	@Autowired
	public void setProductService(ProductServiceImpl productServiceImpl) {
		this.proservice =productServiceImpl;
	
	}
	
	@Autowired
	public void setConService(ConsumptionServiceImpl conServiceImpl)
	{
		this.conservice=conServiceImpl;
	}




	
	@RequestMapping(value = "/list") 
	public String catalog(Model model, @Param("keyword") String keyword) {
		List<Product> products = proservice.listAll(keyword);
		model.addAttribute("products", products);
		model.addAttribute("keyword", keyword);
		return "catalog";
	}
	
	@RequestMapping(value = "/details/{id}")
	public String productDetails(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("product", proservice.findProductbyId(id));
		model.addAttribute("stock", storepo.search(id));
		return "productdetail";
	}
	
	@RequestMapping(value= "/transactionhistory/{id}")
	public String catalog(Model model,@PathVariable("id")Integer id)
	{   
		model.addAttribute("today", LocalDate.now().toString());
		model.addAttribute("consumptions", conservice.listConsumptionsbyProductId(id));
		
		return "transactionhistory";
	}

	@RequestMapping(value = "/transactionhistory/{id}/filter") 
	public String catalog(Model model, @PathVariable("id")Integer id,
							@Param("start")@DateTimeFormat(iso=ISO.DATE) LocalDate start,
							@Param("end")@DateTimeFormat(iso=ISO.DATE) LocalDate end) {
		
		model.addAttribute("today", LocalDate.now().toString());
		List<Consumption> consumptions = conservice.findConsumptionByTransactionIdwithDate(id, start, end);
		
		model.addAttribute("consumptions", consumptions);
		model.addAttribute("start",start);
		model.addAttribute("end",end);

		return "transactionhistory";
	}
	
	
	
	// Creates padding to center a string
		public static String centerString (int width, String s) {
		    return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
		}
		
		//Put your local file path to where you want it to be saved, dynamic name generation will be added later 
		public static String filepath = "C:\\forCa\\forCa.txt";
		
		
		//generates report based on consumptions list passed in
		public static void genReport(List<Consumption> consumptions, LocalDate start, LocalDate end) {
			// for testing
			System.out.println("Triggered");
			
	        try {
	        	File myObj = new File(filepath);
	            if (myObj.createNewFile()) {
	              System.out.println("File created: " + myObj.getName());
	            } else {
	              System.out.println("File already exists.");
	            }
	          } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	          }
	        
	        try {
	            FileWriter myWriter = new FileWriter(filepath);
	            String spacer = "-";
	            String nl = "\r\n"; //carriage return
	            //Heading
	            myWriter.write(spacer.repeat(100) + nl);
	            myWriter.write(centerString(100, "Transaction History") + nl);
	            myWriter.write(spacer.repeat(100) + nl);
	            myWriter.write("Date Range: " + start + ' ' + '-' + ' ' + end + nl);
	            myWriter.write(spacer.repeat(100) + nl);
	            //Table headings
	            myWriter.write(String.format("%1$-5s", "ID"));
	            myWriter.write(String.format("%1$-11s", "Date"));
	            myWriter.write(String.format("%1$-15s", "Car"));
	            myWriter.write(String.format("%1$-15s", "Model"));
	            myWriter.write(String.format("%1$-20s", "Customer Name"));
	            myWriter.write(String.format("%1$-13s", "Consumed Qty") + nl);
	            myWriter.write(spacer.repeat(100) + nl);
	            //data
	    		for (Consumption c:consumptions)
	    		{
	                myWriter.write(String.format("%1$-5s", c.getId()));
	                myWriter.write(String.format("%1$-11s", c.getTransaction().getDate()));
	                myWriter.write(String.format("%1$-15s", c.getTransaction().getCar().getBrand()));
	                myWriter.write(String.format("%1$-15s", c.getTransaction().getCar().getModel()));
	                myWriter.write(String.format("%1$-20s", c.getTransaction().getCar().getOwnerName()));
	                myWriter.write(String.format("%1$-13s", c.getConsumedQty()) + nl);
	    		}
	            myWriter.flush();
	            myWriter.close();
	            System.out.println("Successfully wrote to the file.");
	          } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	          }
		}
		
		@RequestMapping(value = "/transactionhistory/{id}/filter/report") 
		public String GenerateReport(Model model, @PathVariable("id")Integer id,
								@Param("start")@DateTimeFormat(iso=ISO.DATE) LocalDate start,
								@Param("end")@DateTimeFormat(iso=ISO.DATE) LocalDate end,
								HttpServletResponse response) throws IOException {

			model.addAttribute("today", LocalDate.now().toString());
			System.out.println(start);
			System.out.println(end);
			//if no date is input it will generate report for the whole list
			if(start == null && end == null) {
				List<Consumption> consumptions = conservice.listConsumptionsbyProductId(id);
				genReport(consumptions, start, end);
				model.addAttribute("consumptions", consumptions);
				
				// Triggers download
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
				"attachment;filename=filename2.txt");
				File file = new File(filepath);
				FileInputStream fileIn = new FileInputStream(file);
				ServletOutputStream out = response.getOutputStream();

				byte[] outputByte = new byte[4096];
				//copy binary contect to output stream
				while(fileIn.read(outputByte, 0, 4096) != -1)
				{
				    out.write(outputByte, 0, 4096);
				}
				fileIn.close();
				out.flush();
				out.close();
				
			}
			else
			{
				List<Consumption> consumptions = conservice.findConsumptionByTransactionIdwithDate(id, start, end);
				genReport(consumptions, start, end);
				model.addAttribute("consumptions", consumptions);
				// Triggers download
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition",
				"attachment;filename=filename2.txt");
				File file = new File(filepath);
				FileInputStream fileIn = new FileInputStream(file);
				ServletOutputStream out = response.getOutputStream();

				byte[] outputByte = new byte[4096];
				//copy binary contect to output stream
				while(fileIn.read(outputByte, 0, 4096) != -1)
				{
				    out.write(outputByte, 0, 4096);
				}
				fileIn.close();
				out.flush();
				out.close();
				
			}
			

			return "transactionhistory";
		}

}