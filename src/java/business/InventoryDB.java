
package business;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author n.riley
 */
public class InventoryDB {
    public static boolean addBookInv(Inventory inv) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); 
        Session session = null;
        boolean dbstat  = false;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(inv);
            session.getTransaction().commit();
            dbstat = true;
        } catch(HibernateException e) {
            if(session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return dbstat;
    }
    public static boolean delete(Inventory inv) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); 
        Session session = null;
        boolean dbstat = false;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(inv);
            session.delete(inv.getBook());
            session.getTransaction().commit();
            dbstat = true;
        } catch(HibernateException e) {
            if(session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return dbstat;
    } 
}
