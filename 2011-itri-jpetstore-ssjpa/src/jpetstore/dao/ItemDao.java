package jpetstore.dao;

import java.util.List;

import jpetstore.domain.Item;
import jpetstore.domain.Order;

import org.springframework.dao.DataAccessException;

public interface ItemDao
{

	public void updateQuantity(Order order) throws DataAccessException;

	boolean isItemInStock(String itemId) throws DataAccessException;

	List getItemListByProduct(String productId) throws DataAccessException;

	Item getItem(String itemId) throws DataAccessException;

}
