package com.example.DependecyInjectionEx;

public class Vehicle {
    IEngine engine;
    Tyres tyre;

    public Tyres getTyre() {
        return tyre;
    }

    public void setTyre(Tyres tyre) {
        System.out.println("Tyre instantiated via setter!");
        this.tyre = tyre;
    }

    public Vehicle(){
    }

    public Vehicle(IEngine engine, Tyres tyre){
        System.out.println("Instantiated via constructor!");
        this.engine = engine;
        this.tyre = tyre;
    }
    public IEngine getEngine() {
        return engine;
    }

    public void setEngine(IEngine engine) {
        System.out.println("Instantiated via setter!");
        this.engine = engine;
    }

    public String toString(){
        return engine + "," + tyre;
    }
}
