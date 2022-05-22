package org.example;

import org.example.dao.InMemoryOperationStorage;
import org.example.dao.InMemoryUserStorage;
import org.example.entity.Operation;
import org.example.entity.User;
import org.junit.Assert;
import org.junit.Test;

public class InMemoryStorageTest {
    @Test
    public void saveUser() {
        Assert.assertTrue(new InMemoryUserStorage().save(new User(0, "test", "test", "test")));
    }

    @Test
    public void findAllUsers() {
        InMemoryUserStorage inMemoryUserStorage = new InMemoryUserStorage();
        inMemoryUserStorage.save(new User(0, "test", "test", "test"));
        Assert.assertNotNull(inMemoryUserStorage.findAll());
    }

    @Test
    public void findUserByLogin() {
        InMemoryUserStorage inMemoryUserStorage = new InMemoryUserStorage();
        inMemoryUserStorage.save(new User(0, "test", "test", "test"));
        Assert.assertNotNull(inMemoryUserStorage.getUserByLogin("test"));
    }

    @Test
    public void authUser() {
        InMemoryUserStorage inMemoryUserStorage = new InMemoryUserStorage();
        inMemoryUserStorage.save(new User(0, "test", "test", "test"));
        Assert.assertTrue(inMemoryUserStorage.authByUsernameAndPassword("test", "test"));
    }

    @Test
    public void saveOperation() {
        InMemoryOperationStorage inMemoryOperationStorage = new InMemoryOperationStorage();
        Assert.assertTrue(inMemoryOperationStorage.save(new Operation
                (new User(0, "test", "test", "test"),
                        2, 2, "add", 4)));
    }

    @Test
    public void findAllOperationsByUser() {
        InMemoryOperationStorage inMemoryOperationStorage = new InMemoryOperationStorage();
        inMemoryOperationStorage.save(new Operation
                (new User(0, "test", "test", "test"),
                        2, 2, "add", 4));
        Assert.assertNotNull(inMemoryOperationStorage.findAllByUser("test"));
    }

    @Test
    public void findAllOperations() {
        InMemoryOperationStorage inMemoryOperationStorage = new InMemoryOperationStorage();
        inMemoryOperationStorage.save(new Operation
                (new User(0, "test", "test", "test"),
                        2, 2, "add", 4));
        Assert.assertNotNull(inMemoryOperationStorage.findAll());
    }
}
