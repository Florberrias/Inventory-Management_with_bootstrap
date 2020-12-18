package sg.edu.iss.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierId;
	
	@NotEmpty(message="Please fill in brand name")
	@Pattern(regexp = "[a-zA-Z0-9 ]+", message="The brand name should not include special characters")
	private String brandName;
	
	@NotEmpty(message="Please fill in company name")
	@Pattern(regexp = "[a-zA-Z0-9 ]+", message="The company name should not include special characters")
	private String companyName;
	
	@NotEmpty(message="Please fill in contact person")
	@Pattern(regexp = "[a-zA-Z0-9 ]+", message="The contact person should not include special characters")
	private String contactPerson;
	
	@NotNull(message="Please fill in contact number")
	@Pattern(regexp = "^[0-9]{8}$",  message="Please enter valid phone number")
	private String contactNumber;
	
	@NotEmpty(message="Please fill in address")
	@Pattern(regexp = "[a-zA-Z0-9-,# ]+", message="The address should not include special characters")
	private String address;
	
	@NotEmpty(message="Please fill in email address")
	@Email(message="Please enter valid email")
	private String emailAddress;
	
	@OneToMany(mappedBy = "supplier")
	private List<Product> products;

	private SupplierStatus status;
	
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	//tomatchshameer's
	public Supplier(String name, String address, String contactPerson, String  contactNumber, String email,String brandName)
	{
		super();
		this.companyName=name;
		this.address=address;
		this.contactPerson=contactPerson;
		this.contactNumber=contactNumber;
		this.emailAddress=email;
		this.brandName=brandName;
		this.products=new ArrayList<Product>();
		status = SupplierStatus.SUPPLYING;
	}
	
	public void addProduct(Product p)
	{
		products.add(p);
	}
	
	public void removeProduct(Product p)
	{
		products.remove(p);
	}
	
	/*public void addOrder(Order o)
	{
		orders.add(o);
	}*/
	
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String  getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String  contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String addressString) {
		this.address = addressString;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public List<Product> getProducts() {
		return products;
	}


	


	public SupplierStatus getStatus() {
		return status;
	}


	public void setStatus(SupplierStatus status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((brandName == null) ? 0 : brandName.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
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
		Supplier other = (Supplier) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (brandName == null) {
			if (other.brandName != null)
				return false;
		} else if (!brandName.equals(other.brandName))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (contactNumber == null) {
			if (other.contactNumber != null)
				return false;
		} else if (!contactNumber.equals(other.contactNumber))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", brandName=" + brandName + ", companyName=" + companyName
				+ ", contactPerson=" + contactPerson + ", contactNumber=" + contactNumber + ", address=" + address
				+ ", emailAddress=" + emailAddress + ", products=" + products + "]";
	}
	
	
	
	
}

