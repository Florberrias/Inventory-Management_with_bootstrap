package sg.edu.iss.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import sg.edu.iss.model.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Service;

import sg.edu.iss.model.Car;
import sg.edu.iss.model.Consumption;
import sg.edu.iss.model.Product;
import sg.edu.iss.repo.CarRepository;
import sg.edu.iss.repo.ConsumptionRepository;
import sg.edu.iss.repo.TransactionRepository;

@Service
public class ConsumptionServiceImpl implements ConsumptionService{
   
	 @Autowired
	 ConsumptionRepository conrepo;
	 
	 @Autowired
	 TransactionRepository tranrepo;
	 
	 @Autowired
	 CarRepository carrepo;
	@Override
	public List<Consumption> listConsumptionsbyProductId(int productid) {
		return conrepo.findConsumptionByProductId(productid);

	}

	
	@Override
	public List<Consumption> findConsumptionByTranAndProId(Integer tid, Integer pid) {
		// TODO Auto-generated method stub
		return conrepo.findConsumptionByTranAndProId(tid, pid);
	}

	@Override
	public List<Consumption> findConsumptionByTransactionIdwithDate(Integer id, @DateTimeFormat(iso=ISO.DATE)LocalDate start, @DateTimeFormat(iso=ISO.DATE)LocalDate end) {
		List<Transaction> transactionsfound = tranrepo.findTransactionByDateRange(start, end);
		List<Consumption> consumptionsfound = new ArrayList<Consumption>();
		for(Transaction t:transactionsfound)
		{
			
			for(Consumption c: conrepo.findConsumptionByTranAndProId(t.getId(), id))
			{
				consumptionsfound.add(c);
			}
		}
		return consumptionsfound;
		
	
	}


	@Override
	public List<Car> findallCars() {
		return carrepo.findAll();
	}


	@Override
	public boolean saveConsumption(Consumption consumption) {
		if(conrepo.save(consumption)!=null)
		{
			tranrepo.save(new Transaction(consumption.getTransaction().getCar(), consumption.getTransaction().getDate()));
			return true;
		}
		return false;
	}


	

	
}

