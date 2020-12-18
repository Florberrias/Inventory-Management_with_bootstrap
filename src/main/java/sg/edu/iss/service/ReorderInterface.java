package sg.edu.iss.service; 

import java.util.List;  

import sg.edu.iss.model.Reorder;

public interface ReorderInterface {
	
	public void save(Reorder reorder);

	public List<Reorder> list();
	
	// same as above 
	public List<Reorder> getAllReorders();
	
	public Reorder getReorderById(int id);
	
	public void updateOrderQty(Reorder reorder); 
	
	public void updateOrderStatus(Reorder reorder); 
	
	public void addDate(Reorder reorder);

	public void updateDamagedQty(Reorder reorder);

	public void updateDamagedDescription(Reorder reorder); 
	
	public boolean isOrderable(Reorder reorder);
	
	public void updateDateReceived(Reorder reorder);
	
	public void updateStock(Reorder reorder);

}
