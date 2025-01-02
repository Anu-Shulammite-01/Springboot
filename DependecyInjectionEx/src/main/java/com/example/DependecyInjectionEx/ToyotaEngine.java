package com.example.DependecyInjectionEx;

public class ToyotaEngine implements IEngine{
    String company;
    double cost;

    public double getCost() {
        return cost;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String importOrigin() {
        return "Japan";
    }

    @Override
    public double cost() {
        return cost;
    }

    @Override
    public String toString() {
        return "The Engine is from: "+company;
    }
}
