package org.launchcode.models;


import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import javax.persistence.*;


@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    //default (no-argument) constructor
    public Category() {
    }

    public Category (String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
