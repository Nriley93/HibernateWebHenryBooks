
package business;

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
        } catch(Exception e) {
            if(session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
        return dbstat;
    }
}
