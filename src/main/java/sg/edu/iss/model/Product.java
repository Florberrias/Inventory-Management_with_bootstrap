package sg.edu.iss.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;


@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	@NotEmpty(message="it's null!")
	@Pattern(regexp = "[a-zA-Z0-9 ]+", message="The product name should not include special characters")
	private String name;
	
	@NotEmpty(message="Please fill in product description ")
	@Pattern(regexp = "[a-zA-Z0-9 ]+", message="The product descripton should not include special characters")
	private String description;
	
	@NotEmpty(message="Please fill in product type")
	@Pattern(regexp = "[a-zA-Z0-9 ]+", message="The product type should not include special characters")
	private String type;
	
	@NotEmpty(message="Please fill in category")
	@Pattern(regexp = "[a-zA-Z0-9 ]+", message="The category should not include special characters")
	private String category;

	@NotEmpty(message="Please fill in sub-category")
	@Pattern(regexp = "[a-zA-Z0-9 ]+", message="The sub-category should not include special characters")
	private String subCategory;
	
	@NotNull(message="Please fill in price")
	@Positive(message="The price needs to be more than 0")
	@Min(value=0, message="Please enter numbers only")
	@Max(value=999999999, message="Please enter numbers only")
	private float originalPrice;
	
	@NotNull(message="Please fill in price")
	@Positive(message="The price needs to be more than 0")
	@Min(value=0, message="Please enter numbers only")
	@Max(value=999999999, message="Please enter numbers only")
	private float wholesalePrice;
	
	@NotNull(message="Please fill in price")
	@Positive(message="The price needs to be more than 0")
	@Min(value=0, message="Please enter numbers only")
	@Max(value=999999999, message="Please enter numbers only")
	private float retailPrice;
	
	@NotNull(message="Please fill in price")
	@Positive(message="The price needs to be more than 0")
	@Min(value=0, message="Please enter numbers only")
	@Max(value=999999999, message="Please enter numbers only")
	private float partnerPrice;
	
	@NotNull(message="Please fill in MOQ")
	@Min(value=1, message="Min Order Qty has to be more than zero")
	@Digits(fraction = 0, integer = 10, message ="Please enter whole number and not less than 0")
	private int MOQ;
	
	@NotNull(message="Please fill in MOQ")
	@Min(value=1, message="Reorder Qty has to be more than zero")
	@Digits(fraction = 0, integer = 10, message ="Please enter whole number and not less than 0")
	private int reorderQty;
  
	private int supplierId;
	
	@OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
	private Stock stock;
	
	@ManyToOne
	@JoinColumn(name = "supplier_supplierId")
	private Supplier supplier;
	
	@OneToMany(mappedBy = "product")
	private List<Consumption> consumptions;

	@OneToMany(mappedBy = "product")
	private List<Reorder> reorders;
	
	private ProductStatus status;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	

    // with int supplierId for mapping in manage and no supplier
	public Product(String name, String description, String type, String category, String subCategory,
			float originalPrice, float wholesalePrice, float retailPrice, float partnerPrice, int mOQ,
		     int supplierId) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		this.category = category;
		this.subCategory = subCategory;
		this.originalPrice = originalPrice;
		this.wholesalePrice = wholesalePrice;
		this.retailPrice = retailPrice;
		this.partnerPrice = partnerPrice;
		this.MOQ = mOQ;	
		this.supplierId = supplierId;
		this.reorderQty=0;
		status = ProductStatus.IN_USE;
		
	}
	
	//to match with shameer's
	public Product(String name, String description, String type, String category, String subCategory,
			float originalPrice, float retailPrice, float wholesalePrice, float partnerPrice, int reorderQty, int mOQ,
			Supplier supplier) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
		this.category = category;
		this.subCategory = subCategory;
		this.originalPrice = originalPrice;
		this.retailPrice = retailPrice;
		this.wholesalePrice = wholesalePrice;
		this.partnerPrice = partnerPrice;
		this.reorderQty = reorderQty;
		MOQ = mOQ;
		this.supplier = supplier;
		consumptions = new ArrayList<Consumption>();
		reorders= new ArrayList<Reorder>();
		status = ProductStatus.IN_USE;
	}
	
	public ProductStatus getStatus() {
		return status;
	}


	public void setStatus(ProductStatus status) {
		this.status = status;
	}



	public int getReorderQty() {
		return reorderQty;
	}


	public void setReorderQty(int reorderQty) {
		this.reorderQty = reorderQty;
	}


	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategoryString) {
		this.subCategory = subCategoryString;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public float getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(float wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public float getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(float retailPrice) {
		this.retailPrice = retailPrice;
	}

	public float getPartnerPrice() {
		return partnerPrice;
	}

	public void setPartnerPrice(float partnerPrice) {
		this.partnerPrice = partnerPrice;
	}

	public int getMOQ() {
		return MOQ;
	}

	public void setMOQ(int mOQ) {
		MOQ = mOQ;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public List<Consumption> getConsumptions() {
		return consumptions;
	}
	public void setConsumptions(List<Consumption> consumptions) {
		this.consumptions = consumptions;
	}
	public void addConsumption(Consumption consumption) {
		consumptions.add(consumption);
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		if(supplier.getSupplierId()==this.supplierId)
		this.supplier = supplier;
	}
	
	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}


	public List<Reorder> getReorders() {
		return reorders;
	}




	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", description=" + description + ", supplierId="
				+ supplierId + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MOQ;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((consumptions == null) ? 0 : consumptions.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(originalPrice);
		result = prime * result + Float.floatToIntBits(partnerPrice);
		result = prime * result + productId;
		result = prime * result + reorderQty;
		result = prime * result + ((reorders == null) ? 0 : reorders.hashCode());
		result = prime * result + Float.floatToIntBits(retailPrice);
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + ((subCategory == null) ? 0 : subCategory.hashCode());
		result = prime * result + ((supplier == null) ? 0 : supplier.hashCode());
		result = prime * result + supplierId;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + Float.floatToIntBits(wholesalePrice);
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
		Product other = (Product) obj;
		if (MOQ != other.MOQ)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (consumptions == null) {
			if (other.consumptions != null)
				return false;
		} else if (!consumptions.equals(other.consumptions))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(originalPrice) != Float.floatToIntBits(other.originalPrice))
			return false;
		if (Float.floatToIntBits(partnerPrice) != Float.floatToIntBits(other.partnerPrice))
			return false;
		if (productId != other.productId)
			return false;
		if (reorderQty != other.reorderQty)
			return false;
		if (reorders == null) {
			if (other.reorders != null)
				return false;
		} else if (!reorders.equals(other.reorders))
			return false;
		if (Float.floatToIntBits(retailPrice) != Float.floatToIntBits(other.retailPrice))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (subCategory == null) {
			if (other.subCategory != null)
				return false;
		} else if (!subCategory.equals(other.subCategory))
			return false;
		if (supplier == null) {
			if (other.supplier != null)
				return false;
		} else if (!supplier.equals(other.supplier))
			return false;
		if (supplierId != other.supplierId)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (Float.floatToIntBits(wholesalePrice) != Float.floatToIntBits(other.wholesalePrice))
			return false;
		return true;
	}



	
	


	
	
	
}
