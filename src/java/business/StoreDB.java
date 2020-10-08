
package business;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author n.riley
 */
public class StoreDB {
    public static List<Store> getStores() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); 
        Session session = null;
        List<Store> stores = null;
        try {
            session = sessionFactory.openSession();
            String qs = "from Store order by storeName";
            Query q = session.createQuery(qs);
            stores = q.list();
        } catch(HibernateException e) {
            stores = null;
        } finally {
            session.close();
        }
        return stores;
    }
    public static String persistStore(Store s) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); 
        Session session = null;
        String msg="";
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.merge(s);
            session.getTransaction().commit();
            session.flush();
            session.refresh(s);
            msg = "<br>Store updated!";
        } catch(HibernateException e) {
            if(session != null) {
                session.getTransaction().rollback();
            }
            msg = "Store update error: " + e.getMessage();
        } finally{
            session.close();
        }
        return msg;
    }
}
