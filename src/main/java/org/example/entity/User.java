package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    private static long counter = 0L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String name;
    private String password;

    public User(String login, String name, String password) {
//        counter++;
//        this.id = counter;
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public User(long id, String login, String name, String password) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
    }
}
