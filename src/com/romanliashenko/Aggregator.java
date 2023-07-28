package com.romanliashenko;


import com.romanliashenko.model.*;
import com.romanliashenko.view.View;
import com.romanliashenko.view.HtmlView;

import java.io.IOException;

public class Aggregator {
    public static void main(String[] args) throws IOException {
        View view = new HtmlView();
        Model model = new Model(view, new Provider(new HHStrategy()), new Provider(new HabrCareerStrategy()));
        Controller controller = new Controller(model);

        view.setController(controller);
        ((HtmlView) view).userCitySelectEmulationMethod();
    }
}
