package ru.netology.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.data.DataHelperCard;
import ru.netology.page.BuyingPage;

public class TestBuyTravelSadPath extends TestBase {
    // тестирование с использованием утвержденной карты
    // Раздел 'Купить'
    @Test
    @DisplayName("Поля не заполнены. Раздел 'Купить'")
    void shouldErrorTestOfNullByBuy() {
        BuyingPage.buyWithCash();
        BuyingPage.setCardNumber("");
        BuyingPage.setCardMonth("");
        BuyingPage.setCardYear("");
        BuyingPage.setCardOwner("");
        BuyingPage.setCardCVV("");
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageIncorrectFormat();
        BuyingPage.checkMessageRequiredField();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfMonth.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Месяц', остальные поля заполнены верно. Раздел 'Купить'")
    void shouldErrorTestOfMonthByBuy(String month) {
        BuyingPage.buyWithCash();
        BuyingPage.setCardNumber(DataHelperCard.getApprovedCardNumber());
        BuyingPage.setCardMonth(month);
        BuyingPage.setCardYear(DataHelperCard.getYear());
        BuyingPage.setCardOwner(DataHelperCard.getOwner());
        BuyingPage.setCardCVV(DataHelperCard.getCvc());
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfYear.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Год', остальные поля заполнены верно. Раздел 'Купить'")
    void shouldErrorTestOfYearByBuy(String year) {
        BuyingPage.buyWithCash();
        BuyingPage.setCardNumber(DataHelperCard.getApprovedCardNumber());
        BuyingPage.setCardMonth(DataHelperCard.getMonth());
        BuyingPage.setCardYear(year);
        BuyingPage.setCardOwner(DataHelperCard.getOwner());
        BuyingPage.setCardCVV(DataHelperCard.getCvc());
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfOwner.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Владелец', остальные поля заполнены верно. Раздел 'Купить'")
    void shouldErrorTestOfOwnerByBuy(String owner) {
        BuyingPage.buyWithCash();
        BuyingPage.setCardNumber(DataHelperCard.getApprovedCardNumber());
        BuyingPage.setCardMonth(DataHelperCard.getMonth());
        BuyingPage.setCardYear(DataHelperCard.getYear());
        BuyingPage.setCardOwner(owner);
        BuyingPage.setCardCVV(DataHelperCard.getCvc());
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfCvc.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'CVC', остальные поля заполнены верно. Раздел 'Купить'")
    void shouldErrorTestOfCvcByBuy(String cvc) {
        BuyingPage.buyWithCash();
        BuyingPage.setCardNumber(DataHelperCard.getApprovedCardNumber());
        BuyingPage.setCardMonth(DataHelperCard.getMonth());
        BuyingPage.setCardYear(DataHelperCard.getYear());
        BuyingPage.setCardOwner(DataHelperCard.getOwner());
        BuyingPage.setCardCVV(cvc);
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageIncorrectFormat();
    }

    // Раздел 'Купить в кредит'
    @Test
    @DisplayName("Поля не заполнены. Раздел 'Купить в кредит'")
    void shouldErrorTestOfNullByBuyInCred() {
        BuyingPage.buyInCredit();
        BuyingPage.setCardNumber(DataHelperCard.getApprovedCardNumber());
        BuyingPage.setCardMonth("");
        BuyingPage.setCardYear("");
        BuyingPage.setCardOwner("");
        BuyingPage.setCardCVV("");
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageIncorrectFormat();
        BuyingPage.checkMessageRequiredField();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfMonth.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Месяц', остальные поля заполнены верно. Раздел 'Купить'")
    void shouldErrorTestOfMonthByBuyInCred(String month) {
        BuyingPage.buyInCredit();
        BuyingPage.setCardNumber(DataHelperCard.getApprovedCardNumber());
        BuyingPage.setCardMonth(month);
        BuyingPage.setCardYear(DataHelperCard.getYear());
        BuyingPage.setCardOwner(DataHelperCard.getOwner());
        BuyingPage.setCardCVV(DataHelperCard.getCvc());
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfYear.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Год', остальные поля заполнены верно. Раздел 'Купить в кредит'")
    void shouldErrorTestOfYearByBuyInCred(String year) {
        BuyingPage.buyInCredit();
        BuyingPage.setCardNumber(DataHelperCard.getApprovedCardNumber());
        BuyingPage.setCardMonth(DataHelperCard.getMonth());
        BuyingPage.setCardYear(year);
        BuyingPage.setCardOwner(DataHelperCard.getOwner());
        BuyingPage.setCardCVV(DataHelperCard.getCvc());
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfOwner.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'Владелец', остальные поля заполнены верно. Раздел 'Купить в кредит'")
    void shouldErrorTestOfOwnerByBuyInCred(String owner) {
        BuyingPage.buyInCredit();
        BuyingPage.setCardNumber(DataHelperCard.getApprovedCardNumber());
        BuyingPage.setCardMonth(DataHelperCard.getMonth());
        BuyingPage.setCardYear(DataHelperCard.getYear());
        BuyingPage.setCardOwner(owner);
        BuyingPage.setCardCVV(DataHelperCard.getCvc());
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageIncorrectFormat();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/VariantsOfCvc.csv", numLinesToSkip = 1)
    @DisplayName("Неверный ввод в поле 'CVC', остальные поля заполнены верно. Раздел 'Купить в кредит'")
    void shouldErrorTestOfCvcByBuyInCred(String cvc) {
        BuyingPage.buyInCredit();
        BuyingPage.setCardNumber(DataHelperCard.getApprovedCardNumber());
        BuyingPage.setCardMonth(DataHelperCard.getMonth());
        BuyingPage.setCardYear(DataHelperCard.getYear());
        BuyingPage.setCardOwner(DataHelperCard.getOwner());
        BuyingPage.setCardCVV(cvc);
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageIncorrectFormat();
    }
}
