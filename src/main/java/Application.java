import dao.DAO;
import dao.subscriptions.SubscriptionDAO;
import dao.users.UserDAO;
import models.subscriptions.Subscription;
import models.users.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;
import java.sql.Array;
import java.util.ArrayList;

public class Application {

    String USER_DAO ="user_dao";

    protected Array DaoList;

    public static void main(String[] args) {

        SessionFactory factory = null;

        try {
            factory = new Configuration().configure().buildSessionFactory();
            UserDAO engineDAO = new UserDAO(factory);

            final User result = engineDAO.read(213212545);
            System.out.println("Created : " + result);
            System.out.println();

        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    private void createDAO()
    {
        SessionFactory userFactory = null;
        userFactory = new Configuration().configure().buildSessionFactory();
   //     DaoList[USER_DAO] =  new UserDAO(userFactory);

    }
}
