import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import pages.FlightsList;
import pages.LoginPage;
import pages.SearchPage;
import pages.RegistrationPage;

public class POMFlightsTests {
    @BeforeEach
    void setUp() {
        open("https://slqa.ru/cases/DeepSeekFlights/");
        getWebDriver().manage().window().maximize();
    }

    //Автотесты

    // 1. Неуспешный логин
    @Test
    void test01WrongPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "WrongPassword");
        loginPage.isLoginUnsuccessful();
    }



    // 2. Не задана дата
    @Test
    void test02NoDate() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("");
        searchPage.isDepartureDateEmpty();
    }

    // 3. Не найдены рейсы
    @Test
    void test03FlightsNotFound() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("24.11.2025", "Казань", "Париж");

        FlightsList flightsList = new FlightsList();
        flightsList.isNoFlights();
    }

    //4. Неправильный номер паспорта
    @Test
    void test04WrongPassportNumber() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("24.11.2025", "Москва", "Нью-Йорк");

        FlightsList flightsList = new FlightsList();
        flightsList.registerToFirstFlight();

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.passportNumber("jfghb");
        registrationPage.completeRegistration();
        registrationPage.isNumberNot();

        sleep(5000);
    }

    //*. Дополнительное задание
    @Test
    void test05SuccessRegistration() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("24.11.2025", "Казань", "Париж");

        sleep(3000);

        FlightsList flightsList = new FlightsList();
        flightsList.isNoFlights();
        flightsList.newSearch();

        sleep(3000);

        searchPage.search("24.11.2025", "Москва", "Нью-Йорк");
        flightsList.registerToFirstFlight();

        sleep(3000);

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.passportNumber("jfghb");
        registrationPage.completeRegistration();
        registrationPage.isNumberNot();
        registrationPage.passportNumber("46985");
        registrationPage.completeRegistration();
        $("#registrationMessage").shouldHave(text("Регистрация успешно завершена!"));

        sleep(5000);
    }
}
