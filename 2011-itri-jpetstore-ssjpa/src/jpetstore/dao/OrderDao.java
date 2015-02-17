package jpetstore.dao;

import java.util.List;

import jpetstore.domain.Order;

import org.springframework.dao.DataAccessException;

public interface OrderDao
{

    List<Order> getOrdersByUsername(String username) throws DataAccessException;

    Order getOrder(int orderId) throws DataAccessException;

    void insertOrder(Order order) throws DataAccessException;

}
