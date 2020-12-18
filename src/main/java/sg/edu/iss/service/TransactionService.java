package sg.edu.iss.service;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.model.Transaction;

public interface TransactionService {
	List<Transaction> findTransactionByDateRange(LocalDate start, LocalDate end);
}
