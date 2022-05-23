package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor
@Entity
@Table(name = "operation")
public class Operation {
    private static long counter = 0L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private double var1;
    private double var2;
    private String operation;
    private double result;

    public Operation(User user, double var1, double var2, String operation, double result) {
        this.id = counter++;
        this.user = user;
        this.var1 = var1;
        this.var2 = var2;
        this.operation = operation;
        this.result = result;
    }

    public Operation(long id, User user, double var1, double var2, String operation, double result) {
        this.id = id;
        this.user = user;
        this.var1 = var1;
        this.var2 = var2;
        this.operation = operation;
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("The operation of %f %s %f is %f", var1, operation, var2, result);
    }
}
