package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FlightsList {
    SelenideElement
            registerButon = $x("//button[.='Зарегистрироваться']"),
            newSearchButton = $x("//button[.='Новый поиск']"),
            flightsTable = $("#flightsTable");

    public void isNoFlights() {
        flightsTable.shouldHave(text("Рейсов по вашему запросу не найдено."));
    }

    public void registerToFirstFlight() {
        this.registerButon.click();
    }

    public void newSearch() {
        newSearchButton.click();
    }
}
