package sg.edu.iss.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.model.Consumption;

public interface ConsumptionRepository extends JpaRepository<Consumption, Integer> {
   @Query("Select c from Consumption c where c.product.productId = :id")
	List<Consumption> findConsumptionByProductId(@Param("id")Integer id);
   
    @Query("Select c from Consumption c where c.transaction = :id")
  	List<Consumption> findConsumptionByTransactionId(@Param("id")Integer id);
     
     @Query("Select c from Consumption c where c.transaction.id = :tid AND c.product.productId = :pid")
  	List<Consumption> findConsumptionByTranAndProId(@Param("tid")Integer tid, @Param("pid")Integer pid);
   
}