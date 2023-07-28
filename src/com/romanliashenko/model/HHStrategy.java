package com.romanliashenko.model;

import com.romanliashenko.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "https://hh.ru/search/vacancy?text=java+%s&page=%d";



    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        while (true) {
            Document document = getDocument(searchString, page);
            Elements vacanciesList = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
            if (vacanciesList.isEmpty()) {
                break;
            }
            for (Element element : vacanciesList) {
                Elements title = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title");
                Elements salary = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation");
                Elements city = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address");
                Elements companyName = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer");

                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(title.get(0).text());
                vacancy.setSalary(salary.size() > 0 ? salary.text() : "");
                vacancy.setCity(city.get(0).text());
                vacancy.setCompanyName(companyName.get(0).text());
                vacancy.setSiteName("hh.ru");
                vacancy.setUrl(title.get(0).attr("href"));
                vacancies.add(vacancy);
            }
            page++;
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page)).
                userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36")
                .referrer("https://hh.ru")
                .get();
    }
}
