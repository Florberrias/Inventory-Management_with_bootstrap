package sg.edu.iss.service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.model.Product;
import sg.edu.iss.model.ProductStatus;
import sg.edu.iss.model.Supplier;
import sg.edu.iss.model.SupplierStatus;
import sg.edu.iss.repo.SupplierRepository;


@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	SupplierRepository suprepo;

	
	@Override
	@Transactional
	public boolean saveSupplier(Supplier supplier) {
		if(suprepo.save(supplier)!=null) 
			return true; 
		else return false;
		
	}

	@Override
	public ArrayList<Supplier> findAllSuppliers() {
		return (ArrayList<Supplier>)suprepo.findAll();
	}

	@Override
	@Transactional
	public void deleteSupplier(Supplier supplier) {
		suprepo.delete(supplier);
		
	}

	@Override
	@Transactional
	public Supplier findSupplierbyId(Integer id) {
		return suprepo.findById(id).get();
	}

	@Override
	public ArrayList<Integer> findAllSuppliersId() {
		ArrayList<Integer> suppliersId = new ArrayList<Integer>();
		ArrayList<Supplier>suppliers= findAllSuppliers();
		
		for (Supplier supplier : suppliers) {
			suppliersId.add(supplier.getSupplierId());
		}
		return suppliersId;
	}

	@Override
	public Supplier findSupplierbyBrandName(String brandname) {
		return suprepo.findByBrandName(brandname);
		
	}

	@Override
	public Supplier findSupplierbyCompanyName(String companyname) {
		return suprepo.findByCompanyName(companyname);
	}

	@Override
	public Supplier findSupplierbyContactNumber(String contactnumber) {
		return suprepo.findByContactNumber(contactnumber);
	}

	@Override
	public Supplier findSupplierbyAddress(String address) {
		return suprepo.findByAddress(address);
	}

	@Override
	public Supplier findSupplierbyEmailAddress(String emailaddress) {
		return suprepo.findByEmailAddress(emailaddress);
	}
	
	@Override
	public ArrayList<Supplier> findSuppliersByStatus(SupplierStatus status) {
		ArrayList<Supplier> suppliers = findAllSuppliers();
		ArrayList<Supplier> suppliersByStatus = new ArrayList<Supplier>();
		for(Supplier supplier : suppliers) {
			if(supplier.getStatus() == status) {
				suppliersByStatus.add(supplier);
				}
		}

		return suppliersByStatus;
	}

}
