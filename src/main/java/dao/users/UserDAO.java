package dao.users;

import com.sun.istack.NotNull;
import dao.DAO;
import models.subscriptions.Subscription;
import models.users.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class UserDAO {
    //  implements DAO<User, Integer>
    private SessionFactory factory;

    public UserDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    // @Override
    public User read(@NotNull final int model) {

        try (final Session session = factory.openSession()) {

            return session.get(User.class, model);
        }
    }

    public User findByEmail(@NotNull String email) {
        try (final Session session = factory.openSession()) {
            return (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("email", email))
                    .uniqueResult();
        }
    }
}