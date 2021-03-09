package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.BD;
import data.Card;
import data.DataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import page.StartPage;
import java.sql.SQLException;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiplomSQLTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUpEach() {
        BD.clearData();
        open("http://localhost:8080/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Nested
    class PaymentPage {
        @Test
        void should1PaymentByCardWithStatusApproved()  {
            Card card= DataGenerator.getApprovedCard();
            val startPage=new StartPage();
            val paymentPage=startPage.goToPaymentPage();
            paymentPage.fillData(card);
            paymentPage.notificationOk();
            assertEquals("APPROVED",BD.getPaymentStatus());
        }

        @Test
        void should2PaymentByCardWithStatusDecline() {
            Card card= DataGenerator.getDeclinedCard();
            val startPage=new StartPage();
            val paymentPage=startPage.goToPaymentPage();
            paymentPage.fillData(card);
            paymentPage.notificationError();
            assertEquals("DECLINED",BD.getPaymentStatus());
        }

        @Test
        void should3PaymentByNonExistentCard() {
            Card card= DataGenerator.getNonExistentCard();
            val startPage=new StartPage();
            val paymentPage=startPage.goToPaymentPage();
            paymentPage.fillData(card);
            paymentPage.notificationError();
            assertEquals(0,BD.countRecords());
        }
    }

    @Nested
    class CreditPage{
        @Test
        void should4CreditByCardWithStatusApproved()  {
            Card card= DataGenerator.getApprovedCard();
            val startPage=new StartPage();
            val creditPage=startPage.goToCreditPage();
            creditPage.fillData(card);
            creditPage.notificationOk();
            assertEquals("APPROVED",BD.getCreditStatus());
        }

        @Test
        void should5CreditByCardWithStatusDecline() {
            Card card= DataGenerator.getDeclinedCard();
            val startPage=new StartPage();
            val creditPage=startPage.goToCreditPage();
            creditPage.fillData(card);
            creditPage.notificationError();
            assertEquals("DECLINED",BD.getCreditStatus());
        }

        @Test
        void should6PaymentByNonExistentCard() {
            Card card= DataGenerator.getNonExistentCard();
            val startPage=new StartPage();
            val creditPage=startPage.goToCreditPage();
            creditPage.fillData(card);
            creditPage.notificationError();
            assertEquals(0,BD.countRecords());
        }

    }
}
