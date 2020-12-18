package sg.edu.iss.service;

import java.time.LocalDate;   
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.model.OrderStatus;
import sg.edu.iss.model.Reorder;
import sg.edu.iss.repo.ReorderRepository;
import sg.edu.iss.repo.StockRepository;

@Service
public class ReorderImplementation implements ReorderInterface {
	
	@Autowired 
	ReorderRepository rrepo;
	
	@Autowired 
	StockRepository strepo;
	
	@Transactional
	public void save(Reorder reorder) {
		rrepo.save(reorder);
	}

	@Transactional(timeout = 30, readOnly = true)
	public List<Reorder> list() {
		return rrepo.findAll();
	}
	
	// same as above method
	@Override
	public List<Reorder> getAllReorders() {
		// TODO Auto-generated method stub
		return rrepo.findAll();
	}

	@Override
	public Reorder getReorderById(int id) {
		// TODO Auto-generated method stub
		return rrepo.findById(id).get();
	}
	
	@Override
	public void updateOrderQty(Reorder reorder) {
		// TODO Auto-generated method stub
		Reorder dbReorder = getReorderById(reorder.getId());
		dbReorder.setOrderQty(reorder.getOrderQty()); 
		rrepo.flush();
	}

	@Override
	public void updateOrderStatus(Reorder reorder) {
		// TODO Auto-generated method stub
		Reorder dbReorder = getReorderById(reorder.getId());
		if (dbReorder.getStatus() == OrderStatus.PENDING_ORDER) {
			dbReorder.setStatus(OrderStatus.REORDERED);
		}
		else if (dbReorder.getStatus() == OrderStatus.REORDERED) {
			dbReorder.setStatus(OrderStatus.RECEIVED);
		}
		else if (dbReorder.getStatus() == OrderStatus.RECEIVED) {
			dbReorder.setStatus(OrderStatus.ADDED_TO_INVENTORY);
		}
		rrepo.flush();
	}

	@Override
	public void addDate(Reorder reorder) {
		// TODO Auto-generated method stub
		Reorder dbReorder = getReorderById(reorder.getId());
		LocalDate dateOrdered = LocalDate.now();
		dbReorder.setDate(dateOrdered);
		rrepo.flush();
	}
	
	@Override
	public void updateDateReceived(Reorder reorder) {
		// TODO Auto-generated method stub
		Reorder dbReorder = getReorderById(reorder.getId());
		LocalDate dateReceived = LocalDate.now();
		dbReorder.setDateReceived(dateReceived);
		rrepo.flush();
	}

	@Override
	public void updateDamagedQty(Reorder reorder) {
		// TODO Auto-generated method stub
		Reorder dbReorder = getReorderById(reorder.getId());
		dbReorder.setDamagedQty(reorder.getDamagedQty());
		rrepo.flush();
	}

	@Override
	public void updateDamagedDescription(Reorder reorder) {
		// TODO Auto-generated method stub
		Reorder dbReorder = getReorderById(reorder.getId());
		dbReorder.setDamagedDescription(reorder.getDamagedDescription());
		rrepo.flush();
	}
	
	@Override
	public boolean isOrderable(Reorder reorder) {
		// TODO Auto-generated method stub
		Reorder dbReorder = getReorderById(reorder.getId()); 
		if (reorder.getOrderQty() >= dbReorder.getProduct().getMOQ()) {
			return true; 
		}
		else {
			return false;
		}
	}
	
	@Override
	public void updateStock(Reorder reorder) {
		Reorder dbReorder = getReorderById(reorder.getId());
		int currentStock = dbReorder.getProduct().getStock().getUnits();
		int orderQty = reorder.getOrderQty();
		int damagedQty = reorder.getDamagedQty();
		dbReorder.getProduct().getStock().setUnits(currentStock+orderQty-damagedQty);
		strepo.flush();
	}

	
}
