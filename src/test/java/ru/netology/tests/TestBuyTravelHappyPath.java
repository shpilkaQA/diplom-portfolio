package ru.netology.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.AssertsInDataBase;
import ru.netology.data.DataHelperCard;
import ru.netology.data.DataHelperDB;
import ru.netology.page.BuyingPage;

import java.sql.SQLException;

public class TestBuyTravelHappyPath extends TestBase {

    @Test
    @DisplayName("Поля заполнены верно - успешная покупка утвержденной картой")
    void buyByApprovedCard() throws SQLException {
        BuyingPage.buyWithCash();
        BuyingPage.setCardNumber(DataHelperCard.getApprovedCardNumber());
        BuyingPage.setCardMonth(DataHelperCard.getMonth());
        BuyingPage.setCardYear(DataHelperCard.getYear());
        BuyingPage.setCardOwner(DataHelperCard.getOwner());
        BuyingPage.setCardCVV(DataHelperCard.getCvc());
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageSuccessfully();
        AssertsInDataBase.verifyStatusWithApprovedBuy();
    }

    @Test
    @DisplayName("Поля заполнены верно - успешная покупка в кредит утвержденной картой")
    void buyInCreditByApprovedCard() throws SQLException {
        BuyingPage.buyInCredit();
        BuyingPage.setCardNumber(DataHelperCard.getApprovedCardNumber());
        BuyingPage.setCardMonth(DataHelperCard.getMonth());
        BuyingPage.setCardYear(DataHelperCard.getYear());
        BuyingPage.setCardOwner(DataHelperCard.getOwner());
        BuyingPage.setCardCVV(DataHelperCard.getCvc());
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageSuccessfully();
        AssertsInDataBase.verifyStatusWithApprovedByCredit();
    }

    @Test
    @DisplayName("Поля заполнены верно - отказ в покупке отклоненной картой")
    void rejectBuyByDeclinedCard() throws SQLException {
        BuyingPage.buyWithCash();
        BuyingPage.setCardNumber(DataHelperCard.getDeclinedCardNumber());
        BuyingPage.setCardMonth(DataHelperCard.getMonth());
        BuyingPage.setCardYear(DataHelperCard.getYear());
        BuyingPage.setCardOwner(DataHelperCard.getOwner());
        BuyingPage.setCardCVV(DataHelperCard.getCvc());
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageError();
        AssertsInDataBase.verifyStatusWithDeclinedBuy();
    }

    @Test
    @DisplayName("Поля заполнены верно - отказ в покупке в кредит отклоненной картой")
    void rejectBuyInCreditDeclinedCard() throws SQLException {
        BuyingPage.buyInCredit();
        BuyingPage.setCardNumber(DataHelperCard.getDeclinedCardNumber());
        BuyingPage.setCardMonth(DataHelperCard.getMonth());
        BuyingPage.setCardYear(DataHelperCard.getYear());
        BuyingPage.setCardOwner(DataHelperCard.getOwner());
        BuyingPage.setCardCVV(DataHelperCard.getCvc());
        BuyingPage.clickContinueButton();
        BuyingPage.checkMessageError();
        AssertsInDataBase.verifyStatusWithDeclinedByCredit();
    }

    @Test
    @DisplayName("Проверка заполнения полей таблиц БД")
    void verifyDBNotNull() throws SQLException {
        DataHelperDB.verifyPaymentNotNull();
        DataHelperDB.verifyCreditNotNull();
    }

    @Test
    @DisplayName("Проверка соединения таблиц по внешнему ключу")
    void verifyDBId() throws SQLException {
        DataHelperDB.comparisonIDPaymentAndOrder();
        DataHelperDB.comparisonIDCreditAndOrder();
    }
}
