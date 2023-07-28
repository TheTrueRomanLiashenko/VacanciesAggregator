package com.romanliashenko.view;


import com.romanliashenko.Controller;
import com.romanliashenko.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";;

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            String newContent = getUpdatedFileContent(vacancies);
            updateFile(newContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> list) throws IOException {
        try {
            Document document = getDocument();
            Element templateVacancyByElement = document
                    .getElementsByClass("template").first();
            Element patternVacancyByElement = templateVacancyByElement.clone();
            patternVacancyByElement
                    .removeClass("template")
                    .removeAttr("style");
            document
                    .getElementsByAttributeValueEnding("class", "vacancy")
                    .remove();

            for (Vacancy vacancy : list) {
                Element currentElement = patternVacancyByElement.clone();

                currentElement
                        .getElementsByClass("city").first()
                        .text(vacancy.getCity());
                currentElement
                        .getElementsByClass("companyName").first()
                        .text(vacancy.getCompanyName());
                currentElement
                        .getElementsByClass("salary").first()
                        .text(vacancy.getSalary());
                //currentElement.getElementsByAttribute("siteName").first().text(vacancy.getSiteName());

                Element link = currentElement.getElementsByTag("a").first();
                link.text(vacancy.getTitle());
                link.attr("href", vacancy.getUrl());

                templateVacancyByElement.before(currentElement.outerHtml());
            }
            return document.html();
        } catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private void updateFile(String string) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        bufferedWriter.write(string);
        bufferedWriter.close();
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() throws IOException {
        controller.onCitySelect("Odessa");
    }
}
