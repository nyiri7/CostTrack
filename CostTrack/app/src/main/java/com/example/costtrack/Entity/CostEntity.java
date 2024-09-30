package com.example.costtrack.Entity;

public class CostEntity {
    private int ID;
    private String Type;
    private int Amount;
    private String Name;

    public int getCat() {
        return Cat;
    }

    public void setCat(int cat) {
        Cat = cat;
    }

    private int Cat;


    public CostEntity(int ID, String type, int amount, String name,int cat) {
        this.ID = ID;
        Type = type;
        Amount = amount;
        Name = name;
        Cat = cat;
    }

    public CostEntity( String type, int amount, String name,int cat) {
        this.ID = 0;
        Type = type;
        Amount = amount;
        Name = name;
        Cat = cat;
    }

    public int getID() {
        return ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
