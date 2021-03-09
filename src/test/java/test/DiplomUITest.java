package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.Card;
import data.DataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.CsvSource;
import page.StartPage;
import org.junit.jupiter.params.ParameterizedTest;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class DiplomUITest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUpEach() {
        open("http://localhost:8080/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Nested
    class PaymentPage {
        @Test
        void should7PaymentByIncorrectNumberCard() {
            Card card = DataGenerator.getIncorrectNumberCard();
            val startPage = new StartPage();
            val paymentPage = startPage.goToPaymentPage();
            paymentPage.fillData(card);
            assertEquals("Неверный формат", paymentPage.inputInvalid());
        }

        @Test
        void should8PaymentByExpiredMonthCard() {
            Card card = DataGenerator.getExpiredMonthCard();
            val startPage = new StartPage();
            val paymentPage = startPage.goToPaymentPage();
            paymentPage.fillData(card);
            assertEquals("Истек срок действия карты", paymentPage.inputInvalid());
        }

        @ParameterizedTest
        @CsvSource(value = {"'00'", "'13'"})
        void should9PaymentByIncorrectMonthCard(String month) {
            Card card = DataGenerator.getIncorrectMonthCard(month);
            val startPage = new StartPage();
            val paymentPage = startPage.goToPaymentPage();
            paymentPage.fillData(card);
            assertEquals("Неверно указан срок действия карты", paymentPage.inputInvalid());
        }

        @Test
        void should10PaymentByIncorrectFormatMonthCard() {
            Card card = DataGenerator.getIncorrectFormatMonthCard();
            val startPage = new StartPage();
            val paymentPage = startPage.goToPaymentPage();
            paymentPage.fillData(card);
            assertEquals("Неверный формат", paymentPage.inputInvalid());
        }

        @Test
        void should11PaymentByExpiredYearCard() {
            Card card = DataGenerator.getExpiredYearCard();
            val startPage = new StartPage();
            val paymentPage = startPage.goToPaymentPage();
            paymentPage.fillData(card);
            assertEquals("Истёк срок действия карты", paymentPage.inputInvalid());
        }

        @Test
        void should12PaymentByYearMore5Card() {
            Card card = DataGenerator.getYearMore5Card();
            val startPage = new StartPage();
            val paymentPage = startPage.goToPaymentPage();
            paymentPage.fillData(card);
            assertEquals("Неверно указан срок действия карты", paymentPage.inputInvalid());
        }

        @Test
        void should13PaymentByIncorrectYearCard() {
            Card card = DataGenerator.getIncorrectYearCard();
            val startPage = new StartPage();
            val paymentPage = startPage.goToPaymentPage();
            paymentPage.fillData(card);
            assertEquals("Неверный формат", paymentPage.inputInvalid());
        }

        @Test
        void should14PaymentByIncorrectHolderCard() {
            Card card = DataGenerator.getIncorrectHolderCard();
            val startPage = new StartPage();
            val paymentPage = startPage.goToPaymentPage();
            paymentPage.fillData(card);
            assertEquals("Неверный формат", paymentPage.inputInvalid());
        }

        @Test
        void should15PaymentByIncorrectCvcCard() {
            Card card = DataGenerator.getIncorrectCVCCard();
            val startPage = new StartPage();
            val paymentPage = startPage.goToPaymentPage();
            paymentPage.fillData(card);
            assertEquals("Неверный формат", paymentPage.inputInvalid());
        }

    }

    @Nested
    class CreditPage {
        @Test
        void should16PaymentByIncorrectNumberCard() {
            Card card = DataGenerator.getIncorrectNumberCard();
            val startPage = new StartPage();
            val creditPage = startPage.goToCreditPage();
            creditPage.fillData(card);
            assertEquals("Неверный формат", creditPage.inputInvalid());
        }

        @Test
        void should17PaymentByExpiredMonthCard() {
            Card card = DataGenerator.getExpiredMonthCard();
            val startPage = new StartPage();
            val creditPage = startPage.goToCreditPage();
            creditPage.fillData(card);
            assertEquals("Истек срок действия карты", creditPage.inputInvalid());
        }

        @ParameterizedTest
        @CsvSource(value = {"'00'", "'13'"})
        void should18PaymentByIncorrectMonthCard(String month) {
            Card card = DataGenerator.getIncorrectMonthCard(month);
            val startPage = new StartPage();
            val creditPage = startPage.goToCreditPage();
            creditPage.fillData(card);
            assertEquals("Неверно указан срок действия карты", creditPage.inputInvalid());
        }

        @Test
        void should19PaymentByIncorrectFormatMonthCard() {
            Card card = DataGenerator.getIncorrectFormatMonthCard();
            val startPage = new StartPage();
            val creditPage = startPage.goToCreditPage();
            creditPage.fillData(card);
            assertEquals("Неверный формат", creditPage.inputInvalid());
        }

        @Test
        void should20PaymentByExpiredYearCard() {
            Card card = DataGenerator.getExpiredYearCard();
            val startPage = new StartPage();
            val creditPage = startPage.goToCreditPage();
            creditPage.fillData(card);
            assertEquals("Истёк срок действия карты", creditPage.inputInvalid());
        }

        @Test
        void should21PaymentByYearMore5Card() {
            Card card = DataGenerator.getYearMore5Card();
            val startPage = new StartPage();
            val creditPage = startPage.goToCreditPage();
            creditPage.fillData(card);
            assertEquals("Неверно указан срок действия карты", creditPage.inputInvalid());
        }

        @Test
        void should22PaymentByIncorrectYearCard() {
            Card card = DataGenerator.getIncorrectYearCard();
            val startPage = new StartPage();
            val creditPage = startPage.goToCreditPage();
            creditPage.fillData(card);
            assertEquals("Неверный формат", creditPage.inputInvalid());
        }

        @Test
        void should23PaymentByIncorrectHolderCard() {
            Card card = DataGenerator.getIncorrectHolderCard();
            val startPage = new StartPage();
            val creditPage = startPage.goToCreditPage();
            creditPage.fillData(card);
            assertEquals("Неверный формат", creditPage.inputInvalid());
        }

        @Test
        void should24PaymentByIncorrectCvcCard() {
            Card card = DataGenerator.getIncorrectCVCCard();
            val startPage = new StartPage();
            val creditPage = startPage.goToCreditPage();
            creditPage.fillData(card);
            assertEquals("Неверный формат", creditPage.inputInvalid());
        }
    }
}
