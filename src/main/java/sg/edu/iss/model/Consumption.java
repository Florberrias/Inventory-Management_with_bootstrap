package sg.edu.iss.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Consumption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "transactionId")
	private Transaction transaction;
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	private int consumedQty;
	public Consumption() {
		super();
	}
	public Consumption(Transaction transaction, Product product, int consumedQty) {
		super();
		this.transaction = transaction;
		this.product = product;
		this.consumedQty = consumedQty;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction usage) {
		this.transaction = usage;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getConsumedQty() {
		return consumedQty;
	}
	public void setConsumedQty(int consumedQty) {
		this.consumedQty = consumedQty;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Consumption [id=" + id + ", product=" + product + ", consumedQty=" + consumedQty + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + consumedQty;
		result = prime * result + id;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((transaction == null) ? 0 : transaction.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumption other = (Consumption) obj;
		if (consumedQty != other.consumedQty)
			return false;
		if (id != other.id)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.transaction))
			return false;
		return true;
	}

}