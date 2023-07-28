# VacanciesAggregator
__Hello everyone!__

Welcome to my ___VacanciesAggregator___ project!

This project was created to search vacancies for request "java Dnipro" via online-recruiting sites, such as ___HH.ru___ and ___career.habr.com___, and
aggregate them using ___Jsoup___ library into one file __vacancies.html__, located in the package _view_. As a result, we have .html file that 
we can open in any browser and look all the vacancies from aforementioned sites for this request. And that's it! =)

I also tried to use ___MVC pattern___ while doing this project. __Model__ is located in package _model_. It uses __View__ (in the package _view_) class 
instance to select view method (currently there's only HTML view), and an array of Provider class instances which provide us data from different
resouces. Providers use certain Strategies, and every strategy is responsible for only one certain Internet resource. That way, we can add a 
lot of them in our Model, and it will aggregate all the requested data.

To use Model, we have __Controller__ class, that can manipulate dirrectly what to do with Model instance. For now it has only one method onCitySelect(String cityName)
which adds to the request "java " a _cityName_. It can be any city you want, so feel free to try it =)

Thanks for reading, and good luck!

___NOTE:___ It's required _the latest version of jsoup_ library to be installed! Otherwise the programm won't work!
