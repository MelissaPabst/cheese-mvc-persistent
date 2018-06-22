package org.launchcode.models;


import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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


    //will represent the list of all items in a given category
    //Use category_id column of cheese table to determine which cheese can belong to a given category
    //one category for many cheeses
    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Cheese> cheeses = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public void setCheeses(List<Cheese> cheeses) {
        this.cheeses = cheeses;
    }
}
