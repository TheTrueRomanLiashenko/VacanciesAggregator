package com.romanliashenko.view;


import com.romanliashenko.Controller;
import com.romanliashenko.vo.Vacancy;

import java.util.List;

public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
