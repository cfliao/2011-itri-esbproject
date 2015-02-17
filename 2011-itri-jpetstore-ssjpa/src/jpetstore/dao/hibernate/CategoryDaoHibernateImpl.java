package jpetstore.dao.hibernate;

import java.util.List;

import jpetstore.dao.CategoryDao;
import jpetstore.domain.Category;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CategoryDaoHibernateImpl extends HibernateDaoSupport implements CategoryDao
{

    public static void main(String[] args)
    {
        ApplicationContext container = new ClassPathXmlApplicationContext("jpetstore/dao/hibernate/beans-config.xml");
        CategoryDao dao = (CategoryDao) container.getBean("categoryDao");

        Category category = dao.getCategory("BIRDS");
        System.out.println(category.getDescription());
    }

    public Category getCategory(final String categoryId) throws DataAccessException
    {
        return (Category) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                return session.createCriteria(Category.class).add(Expression.eq("categoryId", categoryId)).uniqueResult();
            }
        });
    }

    public List getCategoryList() throws DataAccessException
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
        return getHibernateTemplate().findByCriteria(criteria);
    }

}
