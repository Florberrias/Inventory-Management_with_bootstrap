package sg.edu.iss.repo;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
	

	@Query("Select t from Transaction t where t.date BETWEEN :start AND :end")
	List<Transaction> findTransactionByDateRange(@Param("start")LocalDate start, @Param("end")LocalDate end);
}