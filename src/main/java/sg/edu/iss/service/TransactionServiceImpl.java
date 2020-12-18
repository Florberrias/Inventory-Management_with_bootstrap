package sg.edu.iss.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.model.Transaction;
import sg.edu.iss.repo.ConsumptionRepository;
import sg.edu.iss.repo.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository tranrepo;
	
	@Autowired
	ConsumptionRepository conrepo;
	
	

	@Override
	public List<Transaction> findTransactionByDateRange(LocalDate start, LocalDate end) {
	
		return tranrepo.findTransactionByDateRange(start, end);
	}

	}
