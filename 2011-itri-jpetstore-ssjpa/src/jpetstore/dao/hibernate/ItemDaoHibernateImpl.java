package jpetstore.dao.hibernate;

import java.util.List;

import jpetstore.dao.ItemDao;
import jpetstore.domain.Inventory;
import jpetstore.domain.Item;
import jpetstore.domain.LineItem;
import jpetstore.domain.Order;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ItemDaoHibernateImpl extends HibernateDaoSupport implements ItemDao
{

    public Item getItem(final String itemId) throws DataAccessException
    {
        return (Item) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                return session.createCriteria(Item.class).add(Expression.eq("itemId", itemId)).uniqueResult();
            }
        });
    }

    public List getItemListByProduct(final String productId) throws DataAccessException
    {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                Criteria criteria = session.createCriteria(Item.class);
                criteria.add(Expression.eq("productId", productId));
                return criteria.list();
            }
        });
    }

    public boolean isItemInStock(String itemId) throws DataAccessException
    {
        Inventory inventory = this.getInventory(itemId); // 如果寫成一行的話，會Data Access二次。
        return inventory != null && inventory.getQuantity() > 0;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void updateQuantity(Order order) throws DataAccessException
    {
        for (int i = 0; i < order.getLineItems().size(); i++)
        {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Inventory inventory = getInventory(lineItem.getItemId());

            if (inventory != null && (inventory.getQuantity() - lineItem.getQuantity()) >= 0)
            {
                inventory.setQuantity(inventory.getQuantity() - lineItem.getQuantity());
                getHibernateTemplate().update(inventory);
            }
        }
    }

    private Inventory getInventory(final String itemId)
    {
        return (Inventory) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                Criteria criteria = session.createCriteria(Inventory.class);
                criteria.add(Expression.eq("itemId", itemId));
                return criteria.uniqueResult();
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        ApplicationContext container = new ClassPathXmlApplicationContext("jpetstore/dao/hibernate/beans-config.xml");
        ItemDao dao = (ItemDao) container.getBean("itemDao");
        System.out.println(dao.getItem("EST-10").getProduct());

    }
}
