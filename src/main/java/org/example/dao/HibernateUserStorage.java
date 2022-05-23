package org.example.dao;

import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class HibernateUserStorage implements UserStorage {
    private SessionFactory sessionFactory;

    public HibernateUserStorage(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean save(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
        return true;
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("from User", User.class).getResultList();
        session.close();
        return users;
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            Session session = sessionFactory.openSession();
            User user = session
                    .createQuery("from User where login=:login", User.class)
                    .setParameter("login", login).getSingleResult();
            session.close();
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean authByUsernameAndPassword(String login, String password) {
        Session session = sessionFactory.openSession();
        User user = session
                .createQuery("from User where login=:login", User.class)
                .setParameter("login", login).getSingleResult();
        session.close();
        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
