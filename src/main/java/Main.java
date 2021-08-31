import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.HibernateUtil;

public class Main {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static void main(String[] args) {
        sessionFactory = HibernateUtil.getSessionFactory();

        session = sessionFactory.openSession();
//        String hql = "From Password";
//        session.createQuery(hql).list();
        session.close();
    }
}
