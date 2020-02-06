package ru.netology.data;

import io.qameta.allure.Step;
import lombok.val;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Методы, вызывающие методы с запросами к БД
public class AssertsInDataBase {

    @Step("Сверка со статусом 'APPROVED'. Раздел 'Купить'")
    public static void verifyStatusWithApprovedBuy() throws SQLException {
        val status = DataHelperDB.verifyOrderByPayment();
        assertEquals("APPROVED", status);
    }

    @Step("Сверка со статусом 'DECLINED'. Раздел 'Купить'")
    public static void verifyStatusWithDeclinedBuy() throws SQLException {
        val status = DataHelperDB.verifyOrderByPayment();
        assertEquals("DECLINED", status);
    }

    @Step("Сверка со статусом 'APPROVED'. Раздел 'Купить в кредит'")
    public static void verifyStatusWithApprovedByCredit() throws SQLException {
        val status = DataHelperDB.verifyOrderByPaymentByCredit();
        assertEquals("APPROVED", status);
    }

    @Step("Сверка со статусом 'DECLINED'. Раздел 'Купить в кредит'")
    public static void verifyStatusWithDeclinedByCredit() throws SQLException {
        val status = DataHelperDB.verifyOrderByPaymentByCredit();
        assertEquals("DECLINED", status);
    }
}
