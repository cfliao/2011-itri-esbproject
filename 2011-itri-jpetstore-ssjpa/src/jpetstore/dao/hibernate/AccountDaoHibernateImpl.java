package jpetstore.dao.hibernate;

import java.util.List;

import jpetstore.dao.AccountDao;
import jpetstore.domain.Account;
import jpetstore.domain.Banner;
import jpetstore.domain.UserProfile;
import jpetstore.domain.UserSignon;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AccountDaoHibernateImpl extends HibernateDaoSupport implements AccountDao
{

    public Account getAccount(final String username) throws DataAccessException
    {
        return (Account) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                Criteria criteria = session.createCriteria(Account.class);
                criteria.add(Expression.eq("username", username));
                return criteria.uniqueResult();
            }
        });
    }

    public Account getAccount(final String username, final String password) throws DataAccessException
    {
//        System.out.println("username="+username);
//        System.out.println("password="+password);
        Account result = null;
        UserSignon signon = getUserSignon(username, password);
//        System.out.println("signon="+signon);
        if (signon != null)
        {
            result = (Account) getHibernateTemplate().execute(new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException
                {
                    Criteria criteria = session.createCriteria(Account.class);
                    criteria.add(Expression.eq("username", username));
                    return criteria.uniqueResult();
                }
            });

            result.setPassword(password);

            UserProfile profile = getUserProfile(username);
            result.setFavouriteCategoryId(profile.getFavouriteCategoryId());
            result.setLanguagePreference(profile.getLanguagePreference());
            result.setListOption(profile.isListOption());
            result.setBannerOption(profile.isBannerOption());

            Banner banner = getBanner(result.getFavouriteCategoryId());
            result.setBannerName(banner.getBannerName());
        }
//        System.out.println("Account="+result);
        return result;
    }

    public List getUsernameList() throws DataAccessException
    {
        return null;
    }

    public void insertAccount(Account account) throws DataAccessException
    {

    }

    public void updateAccount(Account account) throws DataAccessException
    {

    }

    private Banner getBanner(final String categoryId)
    {
        return (Banner) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                Criteria criteria = session.createCriteria(Banner.class);
                criteria.add(Expression.eq("favouriteCategoryId", categoryId));
                return criteria.uniqueResult();
            }
        });
    }

//    private UserSignon getUserSignon(final String username)
//    {
//        return (UserSignon) getHibernateTemplate().execute(new HibernateCallback() {
//            public Object doInHibernate(Session session) throws HibernateException
//            {
//                Criteria criteria = session.createCriteria(UserSignon.class);
//                criteria.add(Expression.eq("username", username));
//                return criteria.uniqueResult();
//            }
//        });
//    }

    private UserSignon getUserSignon(final String username, final String password)
    {
        return (UserSignon) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                Criteria criteria = session.createCriteria(UserSignon.class);
                criteria.add(Expression.eq("username", username));
                criteria.add(Expression.eq("password", password));
                return criteria.uniqueResult();
            }
        });
    }

    private UserProfile getUserProfile(final String username)
    {
        return (UserProfile) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException
            {
                Criteria criteria = session.createCriteria(UserProfile.class);
                criteria.add(Expression.eq("username", username));
                return criteria.uniqueResult();
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        ApplicationContext container = new ClassPathXmlApplicationContext("jpetstore/dao/hibernate/beans-config.xml");
        AccountDao dao = (AccountDao) container.getBean("accountDao");

        Account account = dao.getAccount("j2ee", "j2ee");
        System.out.println(account.getUsername());
        System.out.println(account.getFavouriteCategoryId());

    }
}
