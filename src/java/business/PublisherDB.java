
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
public class PublisherDB {
    public static List<Publisher> getPublishers() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory(); 
        Session session = null;
        List<Publisher> publishers = null;
        try {
            session = sessionFactory.openSession();
            String qs = "from Publisher order by Publisher_Name";
            Query q = session.createQuery(qs);
            publishers = q.list();
        } catch(HibernateException e) {
            publishers = null;
        } finally {
            session.close();
        }
        return publishers;
    }

}
