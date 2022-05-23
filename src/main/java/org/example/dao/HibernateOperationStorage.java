package org.example.dao;

import org.example.entity.Operation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateOperationStorage implements OperationStorage {
    private SessionFactory sessionFactory;
    private HibernateUserStorage hibernateUserStorage;

    public HibernateOperationStorage(SessionFactory sessionFactory, HibernateUserStorage hibernateUserStorage) {
        this.sessionFactory = sessionFactory;
        this.hibernateUserStorage = hibernateUserStorage;
    }

    @Override
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
                .setParameter("user", hibernateUserStorage.getUserByLogin(username))
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
}
