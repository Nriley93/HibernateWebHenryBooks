
package business;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author n.riley
 */
public class BookDB {
    public static boolean addBook(Book bk) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); 
        Session session = null;
        boolean dbstat = false;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(bk);
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
    public static void delete(Book bk) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); 
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(bk);
            session.getTransaction().commit();
        } catch(HibernateException e) {
            if(session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    } 
}
