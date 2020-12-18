package sg.edu.iss.service;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.model.Car;
import sg.edu.iss.model.Consumption;
import sg.edu.iss.model.Product;

public interface ConsumptionService {

	public List<Consumption> listConsumptionsbyProductId(int productid);
	List<Consumption> findConsumptionByTransactionIdwithDate(Integer id, LocalDate start, LocalDate end);
	List<Consumption> findConsumptionByTranAndProId(Integer tid, Integer pid);
	List<Car> findallCars ();
	public boolean saveConsumption(Consumption consumption);
}