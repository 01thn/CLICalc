package org.example.dao;

import org.example.entity.Operation;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class HibernateOperationStorage implements OperationStorage {
    private SessionFactory sessionFactory;

    public HibernateOperationStorage(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public boolean save(Operation operation) {
        Session session = sessionFactory.openSession();
        session.save(operation);
        session.close();
        return true;
    }

    @Override
    public List<Operation> findAllByUser(String username) {
        Session session = sessionFactory.openSession();
        List<Operation> operations = session.createQuery("from Operation where user=:user", Operation.class)
                .setParameter("user", getUserByLogin(username))
                .getResultList();
        return operations;
    }

    @Override
    public List<Operation> findAll() {
        Session session = sessionFactory.openSession();
        List<Operation> operations = session.createQuery("from Operation", Operation.class).getResultList();
        session.close();
        return operations;
    }

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
}
