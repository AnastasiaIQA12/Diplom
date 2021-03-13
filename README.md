### **Запуск тестов**

1. склонировать репозиторий `git clone`
2. запустить контейнер с MySql, PostgreSQL и Node.js используя команду `docker-compose up -d --build` (необходим установленный Docker); 
3. запустить приложение:
    * для запуска под MySQL использовать команду   
    ```java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar aqa-shop.jar```
    * для запуска под PostgreSQL использовать команду   
    ```java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar aqa-shop.jar```
4. запустить тесты:
   * для запуска под MySQL использовать команду                                          
   ```gradlew -Ddb.url=jdbc:mysql://localhost:3306/app clean test```
   
   * для запуска под PostgreSQL использовать команду   
   ```gradlew -Ddb.url=jdbc:postgresql://localhost:5432/app clean test   ```
5. после окончания тестов завершить работу приложения (Ctrl+C), остановить контейнеры командой `docker-compose down`   
**Примечание:** Тесты запускаются для "http://localhost:8080/", чтобы изменить адрес, необходимо дополнительно указать -Dsut.url=...
*Чтобы изменить логин и пароль, заданных по умолчанию, для подключения к БД, необходимо дополнительно указать -Ddb.user=... и -Ddb.password=...  
 
6. для получения отчета (Allure) использовать команду `gradlew allureServe`