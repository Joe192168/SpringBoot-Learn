package com.joe.atomikos.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : joe
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Learner implements Serializable {

    private static final long serialVersionUID = -6841665499117483402L;
    private int id;

    private String name;

    private int age;

    private float salary;

    private Date birthday;

    public Learner(String name, int age, float salary, Date birthday){
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.birthday = birthday;
    }
}
