package com.romanliashenko;

import com.romanliashenko.model.Model;

import java.io.IOException;

public class Controller {
    private Model model;

    public Controller(Model model) {
        if (model == null)
            throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(String cityName) throws IOException {
        model.selectCity(cityName);
    }
}
