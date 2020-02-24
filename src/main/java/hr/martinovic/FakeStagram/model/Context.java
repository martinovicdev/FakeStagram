package hr.martinovic.FakeStagram.model;

import hr.martinovic.FakeStagram.utils.Strategy;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public String executeStrategy(String name){
        return strategy.editImage(name);
    }

}
