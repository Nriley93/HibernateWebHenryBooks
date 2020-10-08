
package business;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author n.riley
 */
public class UserDB {
    public static User getUser(int uid) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        User u = null;
        try {
            session = sessionFactory.openSession();
//            String qs = "from User where UserID = :uid";
//            Query q = session.createQuery(qs);
            Query q = session.getNamedQuery("dbget_User");
            q.setInteger("uid", uid);
            u = (User) q.uniqueResult();
            
        } catch(HibernateException e) {
            u = null;
        } finally {
            session.close();
        }
        return u;
    }
} 
