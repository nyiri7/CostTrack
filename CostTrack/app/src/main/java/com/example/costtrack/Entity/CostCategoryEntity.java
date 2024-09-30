package com.example.costtrack.Entity;

public class CostCategoryEntity {
    private int Id;
    private String Name;

    public CostCategoryEntity(String name, int id) {
        Name = name;
        Id = id;
    }

    public CostCategoryEntity(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    @Override
    public String toString() {
        return Name;

    }
}
