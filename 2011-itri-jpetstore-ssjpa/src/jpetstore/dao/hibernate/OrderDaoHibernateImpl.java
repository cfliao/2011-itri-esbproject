package jpetstore.dao.hibernate;

import java.util.Date;
import java.util.List;

import jpetstore.dao.ItemDao;
import jpetstore.dao.OrderDao;
import jpetstore.domain.Item;
import jpetstore.domain.LineItem;
import jpetstore.domain.Order;
import jpetstore.domain.OrderStatus;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class OrderDaoHibernateImpl extends HibernateDaoSupport implements OrderDao
{

    public Order getOrder(final int orderId) throws DataAccessException
    {
        Order order = (Order) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                Criteria criteria = session.createCriteria(Order.class);
                criteria.add(Expression.eq("orderId", new Integer(orderId)));
                return criteria.uniqueResult();
            }
        });

        if (order != null)
        {
            OrderStatus orderStatus = getOrderStatus(order.getOrderId());
            order.setStatus(orderStatus.getStatus());
            order.setLineItems(getLineItemList(orderId));
        }

        return order;
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrdersByUsername(final String username) throws DataAccessException
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Order.class);
        criteria.add(Expression.eq("username", username));
        List<Order> list = (List<Order>) getHibernateTemplate().findByCriteria(criteria);

        for (Order order : list)
        {
            OrderStatus orderStatus = getOrderStatus(order.getOrderId());
            order.setStatus(orderStatus.getStatus());
            order.setLineItems(getLineItemList(order.getOrderId()));
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void insertOrder(Order order) throws DataAccessException
    {
        getHibernateTemplate().save(order); // save order
        getHibernateTemplate().save(buildOrderStatus(order));
        //raiseException();
        List<LineItem> list = order.getLineItems();
        for (LineItem lineItem : list)
        {
            lineItem.setOrderId(order.getOrderId());
            getHibernateTemplate().save(lineItem);
        }
    }

    @SuppressWarnings("unchecked")
    private List<LineItem> getLineItemList(final int orderId)
    {
        return (List<LineItem>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                return session.createCriteria(LineItem.class).add(Expression.eq("orderId", orderId)).list();
            }
        });
    }

//    private void raiseException()
//    {
//        throw new RuntimeException("test only!");
//    }

    private OrderStatus getOrderStatus(final int orderId)
    {
        return (OrderStatus) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                Criteria criteria = session.createCriteria(OrderStatus.class);
                criteria.add(Expression.eq("orderId", new Integer(orderId)));
                return criteria.uniqueResult();
            }
        });
    }

    private OrderStatus buildOrderStatus(Order order)
    {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(order.getOrderId());
        orderStatus.setLineLumber(order.getOrderId()); // �ӭ��ibatis�g��? �ä��X�z.
        orderStatus.setStatus(order.getStatus());
        orderStatus.setTimestamp(new Date());
        return orderStatus;
    }

    public static void main(String[] args)
    {
        ApplicationContext container = new ClassPathXmlApplicationContext("jpetstore/dao/hibernate/beans-config.xml");
        OrderDao orderDao = (OrderDao) container.getBean("orderDao");
        ItemDao itemDao = (ItemDao) container.getBean("itemDao");

        LineItem lineItem = new LineItem();
        Item item = itemDao.getItem("EST-18");
        lineItem.setLineNumber(1);
        lineItem.setItem(item);
        lineItem.setItemId(item.getItemId());
        Order order = new Order();
        order.setUsername("j2ee");
        order.setOrderDate(new Date());
        order.addLineItem(lineItem);
        orderDao.insertOrder(order);

        List<Order> list = orderDao.getOrdersByUsername("j2ee");
        for (Order o : list)
        {
            System.out.println(o);
            System.out.println(o.getStatus());
        }
        // Order o = orderDao.getOrder(34);
        // System.out.println(o.getStatus());
    }

}
