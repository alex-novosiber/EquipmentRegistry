# Сервер магазина бытовой техники.



  Использованные инструменты:
 - [IntellijIdea] - среда разработки
- [Java 8 JDK] - восьмая версия Java
- 

Frameworks: 
- Spring Boot Starter, Spring Web, Spring JPA, Spring Hibernate;
- База данных: Postgres SQL;
- Библиотека для генерации документации: 1.6.12 
-    (springdoc-openapi v1.5.9 не завелась, конфликты зависимостей классов)

  **Для запуска приложения необходимо склонировать репозиторий, собрать его в Веб-архив WAR,**
  **и запустить с помощью Tomcat сервера.**
  - *https://tomcat.apache.org/tomcat-9.0-doc/*



Использованные инструменты:
- [IntellijIdea] - среда разработки
- [Java 8 JDK] - восьмая версия Java
-

Frameworks:
- Spring Boot Starter, Spring Web, Spring JPA, Spring Hibernate;
- База данных: Postgres SQL;
- Библиотека для генерации документации: 1.6.12
-    (springdoc-openapi v1.5.9 не завелась, конфликты зависимостей классов)

**Для запуска приложения необходимо склонировать репозиторий, собрать его в Веб-архив WAR,**
**и запустить с помощью Tomcat сервера.**
- *https://tomcat.apache.org/tomcat-9.0-doc/*



после сборки WAR файла переименуйте его например в 'demo.war',
и поместите в папку '/webapps/' сервера Tomcat ( например .../Tomcat-9/webapps/ )
доступ к приложению запущенному на сервере Tomcat осуществлять по адресу

        *http://localhost/demo/api/v1/...*


для доступа к документации при запуске приложения в IDE используйте адрес
*http://localhost:8080/openapi/swagger-ui/index.html*


### *Для торопливых -ссылка на собраный WAR архив 
  - https://disk.yandex.ru/d/8jn0BxD9VPEang
### *и ссылка на дамп БД (создан с помощью pg_dump.exe от Postgres)*
  - https://disk.yandex.ru/d/VsPa8-pi7_tY7g
имя базы данных - registry
имя пользователя БД - postgres
пароль - rootpass  
(это если не нашли креды в application.properties)

### *Для торопливых -ссылка на собраный WAR архив
- https://disk.yandex.ru/d/8jn0BxD9VPEang
### *и ссылка на дамп БД (создан с помощью pg_dump.exe от Postgres)*
- https://disk.yandex.ru/d/VsPa8-pi7_tY7g
  имя базы данных - registry
  имя пользователя БД - postgres
  пароль - rootpass  
  (это если не нашли креды в application.properties)