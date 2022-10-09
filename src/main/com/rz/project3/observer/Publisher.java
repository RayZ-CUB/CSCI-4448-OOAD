package com.rz.project3.observer;

import java.io.IOException;
import java.util.ArrayList;

public class Publisher {
    private String event;
    private ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        this.observers.add(observer);
    }
    public void removeObserver(Observer observer){
        this.observers.remove(observer);
    }

    public void publish(String event) throws IOException {
        this.event = event;
        for (Observer observer : this.observers){
            observer.update(event);
        }
    }
}
