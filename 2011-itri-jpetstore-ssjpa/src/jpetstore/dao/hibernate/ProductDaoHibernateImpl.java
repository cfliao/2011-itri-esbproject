package jpetstore.dao.hibernate;

import java.util.List;

import jpetstore.dao.ProductDao;
import jpetstore.domain.Product;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ProductDaoHibernateImpl extends HibernateDaoSupport implements ProductDao
{

    public Product getProduct(final String productId) throws DataAccessException
    {
        return (Product) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                return session.createCriteria(Product.class).add(Expression.eq("productId", productId)).uniqueResult();
            }
        });
    }

    public List getProductListByCategory(String categoryId) throws DataAccessException
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
        criteria.add(Expression.eq("categoryId", categoryId));
        return (List) getHibernateTemplate().findByCriteria(criteria);
    }

    public List searchProductList(final String keywords) throws DataAccessException
    {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                return session.createCriteria(Product.class).add(Expression.like("name", keywords)).list();
            }
        });
    }

}
