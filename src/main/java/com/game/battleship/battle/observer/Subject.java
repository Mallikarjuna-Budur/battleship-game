package com.game.battleship.battle.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
	
    private List<Observer> observers;

    protected Subject() {
        this.observers = new ArrayList<>();
    }

    /**
     * Observer Pattern. Method to register observer.
     * @param observer Observer
     */
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Observer Pattern. Method to remove observer.
     * @param observer Observer
     */
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * Observer Pattern. Method to notify observer of changes
     */
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(this);
        }
    }
}
