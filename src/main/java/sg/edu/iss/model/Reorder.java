package sg.edu.iss.model;

import java.time.LocalDate;  

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reorder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// ORM associated with Product
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	private OrderStatus status;
	private int stockUnits;
	@Positive(message="Order quantity must be more than zero") 
	private int orderQty;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateReceived;
	@PositiveOrZero(message="Damaged quantity cannot be null nor negative")
	private int damagedQty;
	@Size(max=255, message="Cannot be more than 255 characters")
	private String damagedDescription;
	
	public Reorder() {
		super();
	}
	
	public Reorder(Product product, OrderStatus status, int stockUnits, int orderQty) {
		super();
		this.product = product;
		this.status = status;
		this.stockUnits = stockUnits;
		this.orderQty = orderQty;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public int getStockUnits() {
		return stockUnits;
	}

	public void setStockUnits(int stockUnits) {
		this.stockUnits = stockUnits;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(LocalDate dateReceived) {
		this.dateReceived = dateReceived;
	}

	public int getDamagedQty() {
		return damagedQty;
	}

	public void setDamagedQty(int damagedQty) {
		this.damagedQty = damagedQty;
	}

	public String getDamagedDescription() {
		return damagedDescription;
	}

	public void setDamagedDescription(String damagedDescription) {
		this.damagedDescription = damagedDescription;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Reorder [id=" + id + ", product=" + product + ", status=" + status + ", orderQty=" + orderQty
				+ ", date=" + date + ", dateReceived=" + dateReceived + ", damagedQty=" + damagedQty
				+ ", damagedDescription=" + damagedDescription + "]";
	}

	
}

