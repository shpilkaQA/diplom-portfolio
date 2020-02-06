package ru.netology.data;

import io.qameta.allure.Step;
import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import ru.netology.models.CreditDataModel;
import ru.netology.models.OrderDataModel;
import ru.netology.models.PaymentDataModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataHelperDB {
    private DataHelperDB() {
    }

    private static Connection conn;
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("user");
    private static String password = System.getProperty("password");

    private static String verificationOrderByPaymentStatus = "SELECT payment_entity.status FROM payment_entity " +
            "JOIN order_entity on payment_entity.transaction_id = order_entity.payment_id " +
            "WHERE payment_entity.created IN (SELECT max(payment_entity.created) FROM payment_entity);";

    private static String verificationOrderByCreditStatus = "SELECT credit_request_entity.status " +
            "FROM credit_request_entity JOIN order_entity " +
            "on credit_request_entity.bank_id = order_entity.credit_id " +
            "WHERE credit_request_entity.created IN (SELECT max(credit_request_entity.created) " +
            "FROM credit_request_entity);";

    private static String checkRowOfCredit = "SELECT * FROM credit_request_entity WHERE created IN (SELECT max(created) " +
            "FROM credit_request_entity);";
    private static String checkRowOfOrder = "SELECT * FROM order_entity WHERE created IN (SELECT max(created) " +
            "FROM order_entity);";
    private static String checkRowOfPayment = "SELECT * FROM payment_entity WHERE created IN (SELECT max(created) " +
            "FROM payment_entity);";

    private static String checkCreditID = "SELECT bank_id FROM credit_request_entity " +
            "WHERE created IN (SELECT max(created) FROM credit_request_entity);";
    private static String checkPaymentID = "SELECT transaction_id FROM payment_entity " +
            "WHERE created IN (SELECT max(created) FROM payment_entity);";
    private static String checkOrderIDByPayment = "SELECT payment_id FROM order_entity " +
            "WHERE created IN (SELECT max(created) FROM order_entity);";
    private static String checkOrderIDByCredit = "SELECT credit_id FROM order_entity " +
            "WHERE created IN (SELECT max(created) FROM order_entity);";

    @Step("Соединение с БД")
    public static Connection getConnectionDB() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                System.out.println("Проблема с подключением к БД - " + ex.getMessage());
            }
        }
        return conn;
    }

    @Step("Закрытие соединения с БД")
    public static void closeConnectionDB() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Невозможно закрыть соединение" + ex.getMessage());
        }
    }

    @Step("Проверка наличия записи в базе о покупке с выводом статуса карты")
    public static String verifyOrderByPayment() throws SQLException {
        val runner = new QueryRunner();
        val order = runner.query(getConnectionDB(), verificationOrderByPaymentStatus, new ScalarHandler<>());
        return String.valueOf(order);
    }

    @Step("Проверка наличия записи в базе о покупке в кредит с выводом статуса карты")
    public static String verifyOrderByPaymentByCredit() throws SQLException {
        val runner = new QueryRunner();
        val order = runner.query(getConnectionDB(), verificationOrderByCreditStatus, new ScalarHandler<>());
        return String.valueOf(order);
    }

    @Step("Проверка наличия записей в таблице credit_request_entity")
    public static void verifyCreditNotNull() throws SQLException {
        val runner = new QueryRunner();
        val orderData = runner.query(getConnectionDB(), checkRowOfOrder, new BeanHandler<>(OrderDataModel.class));
        val creditData = runner.query(getConnectionDB(), checkRowOfCredit, new BeanHandler<>(CreditDataModel.class));
        assertNotNull(orderData);
        assertNotNull(creditData);
    }

    @Step("Проверка наличия записей в таблице payment_entity")
    public static void verifyPaymentNotNull() throws SQLException {
        val runner = new QueryRunner();
        val orderData = runner.query(getConnectionDB(), checkRowOfOrder, new BeanHandler<>(OrderDataModel.class));
        val paymentData = runner.query(getConnectionDB(), checkRowOfPayment, new BeanHandler<>(PaymentDataModel.class));
        assertNotNull(orderData);
        assertNotNull(paymentData);
    }

    @Step("Сравнение внешних ключей в таблицах payment_entity и order_entity")
    public static void comparisonIDPaymentAndOrder() throws SQLException {
        val runner = new QueryRunner();
        val paymentData = runner.query(getConnectionDB(), checkPaymentID, new ScalarHandler<>());
        val orderData = runner.query(getConnectionDB(), checkOrderIDByPayment, new ScalarHandler<>());
        assertEquals(paymentData, orderData, "Payment and Order IDs are not equal");
    }

    @Step("Сравнение внешних ключей в таблицах credit_request_entity и order_entity")
    public static void comparisonIDCreditAndOrder() throws SQLException {
        val runner = new QueryRunner();
        val creditData = runner.query(getConnectionDB(), checkCreditID, new ScalarHandler<>());
        val orderData = runner.query(getConnectionDB(), checkOrderIDByCredit, new ScalarHandler<>());
        assertEquals(creditData, orderData, "Credit and Order IDs are not equal");
    }
}
