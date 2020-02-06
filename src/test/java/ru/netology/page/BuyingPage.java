package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BuyingPage {
    private static String website = System.getProperty("host");
    private static SelenideElement cardNumberField = $$(".input__inner").findBy(text("Номер карты"))
            .$(".input__control");
    private static SelenideElement monthField = $$(".input__inner").findBy(text("Месяц"))
            .$(".input__control");
    private static SelenideElement yearField = $$(".input__inner").findBy(text("Год"))
            .$(".input__control");
    private static SelenideElement cardOwnerField = $$(".input__inner").findBy(text("Владелец"))
            .$(".input__control");
    private static SelenideElement cvcOrCvvField = $$(".input__inner").findBy(text("CVC/CVV"))
            .$(".input__control");
    private static SelenideElement buyButton = $$(".button__text").find(exactText("Купить"));
    private static SelenideElement buyCreditButton = $$(".button__text").find(exactText("Купить в кредит"));
    private static SelenideElement payCard = $$(".heading").find(exactText("Оплата по карте"));
    private static SelenideElement payCreditByCard = $$(".heading")
            .find(exactText("Кредит по данным карты"));
    private static SelenideElement messageSuccessfully = $$(".notification__title").find(exactText("Успешно"));
    private static SelenideElement messageError = $$(".notification__title").find(exactText("Ошибка"));
    private static SelenideElement continueButton = $$(".button__content").find(text("Продолжить"));
    private static SelenideElement incorrectFormat = $(".input_invalid");
    private static SelenideElement requiredField = $$(".input__inner span.input__sub")
            .find(exactText("Поле обязательно для заполнения"));

    @Step("Купить")
    public static void buyWithCash() {
        open(website);
        buyButton.click();
        payCard.shouldBe(visible);
    }

    @Step("Купить в кредит")
    public static void buyInCredit() {
        open(website);
        buyCreditButton.click();
        payCreditByCard.shouldBe(visible);
    }

    @Step("Заполнить поле 'номер карты'")
    public static void setCardNumber(String number) {
        cardNumberField.setValue(number);
    }

    @Step("Заполнить поле 'месяц'")
    public static void setCardMonth(String month) {
        monthField.setValue(month);
    }

    @Step("Заполнить поле 'год'")
    public static void setCardYear(String year) {
        yearField.setValue(year);
    }

    @Step("Заполнить поле 'владелец карты'")
    public static void setCardOwner(String owner) {
        cardOwnerField.setValue(owner);
    }

    @Step("Заполнить поле 'cvv'")
    public static void setCardCVV(String cvc) {
        cvcOrCvvField.setValue(cvc);
    }

    @Step("Нажать кнопку 'продолжить'")
    public static void clickContinueButton() {
        continueButton.click();
    }

    @Step("Сообщение об успешной операции")
    public static void checkMessageSuccessfully() {
        messageSuccessfully.waitUntil(visible, 75000);
    }

    @Step("Сообщение об ошибке при совершении операции")
    public static void checkMessageError() {
        messageError.waitUntil(visible, 75000);
    }

    @Step("Сообщение о неверно заполненном поле")
    public static void checkMessageIncorrectFormat() {
        incorrectFormat.shouldBe(visible);
    }

    @Step("Сообщение об обязательном заполнении поля")
    public static void checkMessageRequiredField() {
        requiredField.shouldBe(visible);
    }
}
