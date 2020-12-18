package sg.edu.iss.repo;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.model.Supplier;




public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	@Query("SELECT s FROM Supplier s WHERE s.brandName=:name")
	Supplier findByBrandName(@Param("name")String name);
	
	@Query("SELECT s FROM Supplier s WHERE s.companyName=:name")
	Supplier findByCompanyName(@Param("name")String name);
	
	@Query("SELECT s FROM Supplier s WHERE s.address=:name")
	Supplier findByAddress(@Param("name")String name);
	
	@Query("SELECT s FROM Supplier s WHERE s.contactNumber=:name")
	Supplier findByContactNumber(@Param("name")String name);
	
	@Query("SELECT s FROM Supplier s WHERE s.emailAddress=:name")
	Supplier findByEmailAddress(@Param("name")String name);
	
}
