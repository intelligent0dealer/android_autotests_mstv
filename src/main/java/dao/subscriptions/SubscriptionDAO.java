package dao.subscriptions;

import com.sun.istack.NotNull;
import dao.DAO;
import models.subscriptions.Subscription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SubscriptionDAO implements DAO<Subscription, String> {
    private SessionFactory factory;

    public SubscriptionDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Subscription read(@NotNull final String model) {

        try (final Session session = factory.openSession()) {
            final Subscription result = session.get(Subscription.class, model);

            return result != null ? result : new Subscription();
        }
    }
}