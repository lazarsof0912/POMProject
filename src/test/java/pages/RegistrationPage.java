package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage {
    SelenideElement
            passportNumber = $("#passportNumber"),
            completeRegistrationButton = $x("//button[.='Завершить регистрацию']"),
            registrationMessage = $("#registrationMessage");

    public void passportNumber(String passportNumber) {
        this.passportNumber.setValue(passportNumber);
    }

    public void completeRegistration() {
        this.completeRegistrationButton.click();
    }

    public void isNumberNot() {
        registrationMessage.shouldHave(text("Номер паспорта должен содержать только цифры и пробелы."));
    }


}
