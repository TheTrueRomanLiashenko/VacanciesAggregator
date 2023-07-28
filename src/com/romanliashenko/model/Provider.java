package com.romanliashenko.model;

import com.romanliashenko.vo.Vacancy;

import java.io.IOException;
import java.util.List;

public class Provider {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Vacancy> getJavaVacancies(String searchString) throws IOException {
        return strategy.getVacancies(searchString);
    }
}
