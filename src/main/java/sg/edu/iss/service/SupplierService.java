package sg.edu.iss.service;

import java.util.ArrayList;

import sg.edu.iss.model.Supplier;
import sg.edu.iss.model.SupplierStatus;

public interface SupplierService {
	 public boolean saveSupplier(Supplier supplier);
	 public ArrayList<Supplier> findAllSuppliers();
	 public void deleteSupplier(Supplier supplier);
	 public Supplier findSupplierbyId(Integer id);
	 public ArrayList<Integer> findAllSuppliersId();
	 public Supplier findSupplierbyBrandName(String brandname);
	 public Supplier findSupplierbyCompanyName(String companyname);
	 public Supplier findSupplierbyContactNumber(String contactnumber);
	 public Supplier findSupplierbyAddress (String address);
	 public Supplier findSupplierbyEmailAddress(String emailaddress);
	 public ArrayList<Supplier> findSuppliersByStatus(SupplierStatus status);
}
