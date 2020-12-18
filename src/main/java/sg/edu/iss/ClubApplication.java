package sg.edu.iss;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.iss.model.Car;
import sg.edu.iss.model.Consumption;
import sg.edu.iss.model.OrderStatus;
import sg.edu.iss.model.Product;
import sg.edu.iss.model.Reorder;
import sg.edu.iss.model.RoleType;
import sg.edu.iss.model.Stock;
import sg.edu.iss.model.StockStatus;
import sg.edu.iss.model.Supplier;
import sg.edu.iss.model.Transaction;
import sg.edu.iss.model.User;
import sg.edu.iss.repo.CarRepository;
import sg.edu.iss.repo.ConsumptionRepository;
import sg.edu.iss.repo.ProductRepository;
import sg.edu.iss.repo.ReorderRepository;
import sg.edu.iss.repo.StockRepository;
import sg.edu.iss.repo.SupplierRepository;
import sg.edu.iss.repo.TransactionRepository;
import sg.edu.iss.repo.UserRepository;

@SpringBootApplication
public class ClubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClubApplication.class, args);
	}
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private ProductRepository prepo;
	
	@Autowired
	private SupplierRepository srepo;
	
	@Autowired
	private StockRepository strepo;
	
	@Autowired
	private ReorderRepository rrepo;
	
	@Autowired
	private CarRepository crepo;

	
	@Autowired
	private TransactionRepository trepo;
	
	@Autowired
	private ConsumptionRepository consrepo;
	
	@Bean
	CommandLineRunner runner() {
		return args -> {
			urepo.save(new User("admin1", "password", "admin1@gmail.com", 
					RoleType.ADMIN));
			urepo.save(new User("admin2", "password", "admin2@gmail.com", 
					RoleType.ADMIN));
			urepo.save(new User("admin3", "password", "admin3@gmail.com", 
					RoleType.ADMIN));
			urepo.save(new User("admin4", "password", "admin4@gmail.com", 
					RoleType.ADMIN));
			urepo.save(new User("admin5", "password", "admin5@gmail.com", 
					RoleType.ADMIN));
			urepo.save(new User("mechanic1", "password", "mechanic1@gmail.com", 
					RoleType.MECHANIC));
			urepo.save(new User("mechanic2", "password", "mechanic2@gmail.com", 
					RoleType.MECHANIC));
			urepo.save(new User("mechanic3", "password", "mechanic3@gmail.com", 
					RoleType.MECHANIC));
			urepo.save(new User("mechanic4", "password", "mechanic4@gmail.com", 
					RoleType.MECHANIC));
			urepo.save(new User("mechanic5", "password", "mechanic5@gmail.com", 
					RoleType.MECHANIC));
			
			Supplier s1 = new Supplier("Toyota Distributors", 
					"7 Toa Payoh Industrial Park, Singapore 319059", "Ali", "98765432", 
					"alimani@gmail.com",  "Toyota");
			Product p1 = new Product("Royal Maxpider Car Mat", "comfortable car mat", 
					"accessories", "mat", "driver mat", 100, 150, 130, 120, 10, 50, s1);
			s1.addProduct(p1);
			srepo.save(s1);
			prepo.save(p1);
			
			Supplier s2 = new Supplier("Honda Distributors", 
					"69 Tampines Industrial Park, Singapore 325674", "Bobby","98765432", 
					"bobbyfir@gmail.com",  "Honda");
			Product p2 = new Product("Turanza T005 Tyre", "a very good tyre", 
					"expendable", "tyre", "tyre", 150, 200, 180, 170, 10, 50, s2);
			s2.addProduct(p2);
			srepo.save(s2);
			prepo.save(p2);
			
			Supplier s3 = new Supplier("Mazda Distributors", 
					"4 Simei Industrial Park, Singapore 346038", "Charlie", "98765432", 
					"chaplin@gmail.com",  "Mazda");
			Product p3 = new Product("Ultra shade", "provide sun shade", 
					"accessories", "visor", "door visor", 150, 200, 180, 170, 10, 50, s3);
			s3.addProduct(p3);
			srepo.save(s3);
			prepo.save(p3);
			
			Supplier s4 = new Supplier("Hyundai Distributors", 
					"89 Toa Payoh Industrial Park, Singapore 234086", "Park", "98765432", 
					"jisung@gmail.com",  "Hyundai");
			Product p4 = new Product("The Powerful Light", "lighting for the car", 
					"electrical", "light", "headlight", 210, 260, 240, 230, 10, 50, s4);
			s4.addProduct(p4);
			srepo.save(s4);
			prepo.save(p4);
			
			Supplier s5 = new Supplier("Lexus Distributors", 
					"12 Kallang Industrial Park, Singapore 644009", "Biscuit", "98765432", 
					"cracker@gmail.com",  "Lexus");
			Product p5 = new Product("Steel Rim", "tyre rim", 
					"expendable", "rim", "rim", 200, 250, 230, 220, 10, 50, s5);
			s5.addProduct(p5);
			srepo.save(s5);
			prepo.save(p5);
			
			Supplier s6 = new Supplier("Kia Distributors", 
					"4 Geylang Industrial Park, Singapore 213084", "Pai", "98765432", 
					"paikia@gmail.com",  "Kia");
			Product p6 = new Product("Auto gear box", "gear box for auto car", 
					"mechanical", "gearbox", "auto gearbox", 500, 550, 530, 520, 10, 
					50, s6);
			s6.addProduct(p6);
			srepo.save(s6);
			prepo.save(p6);
			
			Supplier s7 = new Supplier("BMW Distributors", 
					"Bukit Timah Industrial Park, Singapore 570934", "Richman", "98765432", 
					"iamrich@gmail.com",  "BMW");
			Product p7 = new Product("Model RTX clutch", "2020 clutch model", 
					"mechanical", "clutch", "clutch", 340, 390, 370, 360, 10, 50, s7);
			s7.addProduct(p7);
			srepo.save(s7);
			prepo.save(p7);
			
			Supplier s8 = new Supplier("Mercedes Distributors", 
					"45 Queenstown Industrial Park, Singapore 363095", "Hamilton", 
					"98765432", "lewis@gmail.com", "Mercedes");
			Product p8 = new Product("Gold Dashboard", "dashboard for the rich", 
					"accessories", "board", "dashboard", 140, 190, 170, 160, 10, 50, s8);
			s8.addProduct(p8);
			srepo.save(s8);
			prepo.save(p8);
			
			Supplier s9 = new Supplier("Audi Distributors", 
					"76 Jurong Industrial Park, Singapore 630345", "Ken", "98765432", 
					"watanabe@gmail.com",  "Audi");
			Product p9 = new Product("The door", "strong door", 
					"accessories", "door", "driver door", 240, 290, 270, 260, 10, 50, s9);
			s9.addProduct(p9);
			srepo.save(s9);
			prepo.save(p9);
			
			Supplier s10 = new Supplier("Nissan Distributors", 
					"67 Woodlands Industrial Park, Singapore 585340", "Sunny", "98765432", 
					"sunny@gmail.com",  "Nissan");
			Product p10 = new Product("Steering Wheel", "wheel for steering", 
					"accessories", "steering wheel", "powered steering wheel", 230, 280, 
					260, 250, 10, 50, s10);
			s10.addProduct(p10);
			srepo.save(s10);
			prepo.save(p10);
			
			Stock st1 = new Stock(p1, 30, StockStatus.IN_STOCK);
			strepo.save(st1);
			p1.setStock(st1);
			prepo.save(p1);
			
			Stock st2 = new Stock(p2, 0, StockStatus.REORDER_PLACED);
			strepo.save(st2);
			p2.setStock(st2);
			prepo.save(p2);
			
			Stock st3 = new Stock(p3, 5, StockStatus.REORDER_PLACED);
			strepo.save(st3);
			p3.setStock(st3);
			prepo.save(p3);
			
			Stock st4 = new Stock(p4, 20, StockStatus.IN_STOCK);
			strepo.save(st4);
			p4.setStock(st4);
			prepo.save(p4);
			
			Stock st5 = new Stock(p5, 5, StockStatus.BELOW_REORDER_LEVEL);
			strepo.save(st5);
			p5.setStock(st5);
			prepo.save(p5);
			
			Stock st6 = new Stock(p6, 9, StockStatus.BELOW_REORDER_LEVEL);
			strepo.save(st6);
			p6.setStock(st6);
			prepo.save(p6);
			
			Stock st7 = new Stock(p7, 3, StockStatus.BELOW_REORDER_LEVEL);
			strepo.save(st7);
			p7.setStock(st7);
			prepo.save(p7);
			
			Stock st8 = new Stock(p8, 50, StockStatus.IN_STOCK);
			strepo.save(st8);
			p8.setStock(st8);
			prepo.save(p8);
			
			Stock st9 = new  Stock(p9, 45, StockStatus.IN_STOCK);
			strepo.save(st9);
			p9.setStock(st9);
			prepo.save(p9);
			
			Stock st10 = new  Stock(p10, 25, StockStatus.IN_STOCK);
			strepo.save(st10);
			p10.setStock(st10);
			prepo.save(p10);
			
			DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			Reorder r1 = new Reorder(p1, OrderStatus.ADDED_TO_INVENTORY, 3, 50);
			r1.setDate(LocalDate.parse("06/05/2020", df));
			r1.setDamagedQty(2);
			r1.setDateReceived(LocalDate.parse("09/05/2020", df));
			rrepo.save(r1);
			
			Reorder r2 = new Reorder(p4, OrderStatus.ADDED_TO_INVENTORY, 0, 50);
			r2.setDate(LocalDate.parse("06/05/2020", df));
			r2.setDamagedQty(4);
			r2.setDateReceived(LocalDate.parse("09/05/2020", df));
			rrepo.save(r2);
			
			Reorder r3 = new Reorder(p8, OrderStatus.ADDED_TO_INVENTORY, 5, 50);
			r3.setDate(LocalDate.parse("06/05/2020", df));
			r3.setDamagedQty(1);
			r3.setDateReceived(LocalDate.parse("09/05/2020", df));
			rrepo.save(r3);
			
			Reorder r4 = new Reorder(p9, OrderStatus.ADDED_TO_INVENTORY, 2, 50);
			r4.setDate(LocalDate.parse("06/05/2020", df));
			r4.setDamagedQty(3);
			r4.setDateReceived(LocalDate.parse("09/05/2020", df));
			rrepo.save(r4);
			
			Reorder r5 = new Reorder(p10, OrderStatus.ADDED_TO_INVENTORY, 5, 50);
			r5.setDate(LocalDate.parse("06/05/2020", df));
			r5.setDamagedQty(5);
			r5.setDateReceived(LocalDate.parse("09/05/2020", df));
			rrepo.save(r5);
			
			Reorder r6 = new Reorder(p2, OrderStatus.RECEIVED, 0, 60);
			r6.setDate(LocalDate.parse("06/12/2020", df));
			r6.setDateReceived(LocalDate.parse("09/12/2020", df));
			rrepo.save(r6);
			
			Reorder r7 = new Reorder(p3, OrderStatus.REORDERED, 5, 55);
			r7.setDate(LocalDate.parse("06/12/2020", df));
			rrepo.save(r7);
			
			rrepo.save(new Reorder(p5, OrderStatus.PENDING_ORDER, 5, 51));
			rrepo.save(new Reorder(p6, OrderStatus.PENDING_ORDER, 9, 55));
			rrepo.save(new Reorder(p7, OrderStatus.PENDING_ORDER, 3, 57));
			
			//choose car
			Car c1 = new Car("Audi", "RX8", "Alan", 95421244, "alan@gmail.com");
			crepo.save(c1);
			//add transaction
			Transaction t1 = new Transaction(c1, LocalDate.parse("06/12/2020", df));
			trepo.save(t1);
			//add part
			Consumption cons1 = new Consumption(t1, p1, 4);
			consrepo.save(cons1);
			p1.addConsumption(cons1);
			prepo.save(p1);
			//add part
			Consumption cons2 = new Consumption(t1, p2, 5);
			consrepo.save(cons2);
			p2.addConsumption(cons2);
			prepo.save(p2);
			//add part
			Consumption cons3 = new Consumption(t1, p3, 2);
			consrepo.save(cons3);
			p3.addConsumption(cons3);
			prepo.save(p3);
			
			Car c2 = new Car("BMW", "M3", "Ben", 81341984, "ben@gmail.com");
			crepo.save(c2);
			Transaction t2 = new Transaction(c2, LocalDate.parse("06/11/2020", df));
			trepo.save(t2);
			Consumption cons4 = new Consumption(t2, p10, 4);
			consrepo.save(cons4);
			p10.addConsumption(cons4);
			prepo.save(p10);
			Consumption cons5 = new Consumption(t2, p7, 2);
			consrepo.save(cons5);
			p7.addConsumption(cons5);
			prepo.save(p7);
			Consumption cons6 = new Consumption(t2, p5, 1);
			consrepo.save(cons6);
			p5.addConsumption(cons6);
			prepo.save(p5);
			
			Car c3 = new Car("Mercedes", "Benz-Mclaren", "Connor", 67340532, 
					"connor@gmail.com");
			crepo.save(c3);
			Transaction t3 = new Transaction(c3, LocalDate.parse("06/02/2020", df));
			trepo.save(t3);
			Consumption cons7 = new Consumption(t3, p1, 6);
			consrepo.save(cons7);
			p1.addConsumption(cons7);
			prepo.save(p1);
			Consumption cons8 = new Consumption(t3, p4, 10);
			consrepo.save(cons8);
			p4.addConsumption(cons8);
			prepo.save(p4);
			
			Car c4 = new Car("Toyota", "Corolla", "Dick", 95532365, "dick@gmail.com");
			crepo.save(c4);
			Transaction t4 = new Transaction(c4, LocalDate.parse("06/06/2020", df));
			trepo.save(t4);
			Consumption cons9 = new Consumption(t4, p6, 4);
			consrepo.save(cons9);
			p6.addConsumption(cons9);
			prepo.save(p6);
			Consumption cons10 = new Consumption(t4, p7, 5);
			consrepo.save(cons10);
			p7.addConsumption(cons10);
			prepo.save(p7);
			Consumption cons11 = new Consumption(t4, p8, 6);
			consrepo.save(cons11);
			p8.addConsumption(cons11);
			prepo.save(p8);
			
			Car c5 = new Car("Nissan", "Sunny", "Fuller", 82350293, "fuller@gmail.com");
			crepo.save(c5);
			Transaction t5 = new Transaction(c5, LocalDate.parse("01/12/2020", df));
			trepo.save(t5);
			Consumption cons12 = new Consumption(t5, p9, 7);
			consrepo.save(cons12);
			p9.addConsumption(cons12);
			prepo.save(p9);
			Consumption cons13 = new Consumption(t5, p2, 2);
			consrepo.save(cons13);
			p2.addConsumption(cons13);
			prepo.save(p2);
			Consumption cons14 = new Consumption(t5, p3, 6);
			consrepo.save(cons14);
			p3.addConsumption(cons14);
			prepo.save(p3);
			Consumption cons15 = new Consumption(t5, p8, 3);
			consrepo.save(cons15);
			p8.addConsumption(cons15);
			prepo.save(p8);
			
		};
	}


}



