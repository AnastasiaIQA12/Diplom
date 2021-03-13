package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.BD;
import data.Card;
import data.DataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditPageTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void should1CreditByCardWithStatusApproved()  {
        BD.clearData();
        Card card= DataGenerator.getApprovedCard();
        val startPage=new StartPage();
        val creditPage=startPage.goToCreditPage();
        creditPage.fillData(card);
        creditPage.waitNotificationOk();
        assertEquals("APPROVED",BD.getCreditStatus());
    }

    @Test
    void should2CreditByCardWithStatusDecline() {
        BD.clearData();
        Card card= DataGenerator.getDeclinedCard();
        val startPage=new StartPage();
        val creditPage=startPage.goToCreditPage();
        creditPage.fillData(card);
        creditPage.waitNotificationError();
        assertEquals("DECLINED",BD.getCreditStatus());
    }

    @Test
    void should3PaymentByNonExistentCard() {
        BD.clearData();
        Card card= DataGenerator.getNonExistentCard();
        val startPage=new StartPage();
        val creditPage=startPage.goToCreditPage();
        creditPage.fillData(card);
        creditPage.waitNotificationError();
        assertEquals(0,BD.countRecords());
    }

    @Test
    void should4PaymentByIncorrectNumberCard() {
        Card card = DataGenerator.getIncorrectNumberCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void should5PaymentByExpiredMonthCard() {
        Card card = DataGenerator.getExpiredMonthCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Истек срок действия карты", creditPage.getInputInvalid());
    }

    @ParameterizedTest
    @CsvSource(value = {"'00'", "'13'"})
    void should6PaymentByIncorrectMonthCard(String month) {
        Card card = DataGenerator.getIncorrectMonthCard(month);
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверно указан срок действия карты", creditPage.getInputInvalid());
    }

    @Test
    void should7PaymentByIncorrectFormatMonthCard() {
        Card card = DataGenerator.getIncorrectFormatMonthCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void should8PaymentByExpiredYearCard() {
        Card card = DataGenerator.getExpiredYearCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Истёк срок действия карты", creditPage.getInputInvalid());
    }

    @Test
    void should9PaymentByYearMore5Card() {
        Card card = DataGenerator.getYearMore5Card();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверно указан срок действия карты", creditPage.getInputInvalid());
    }

    @Test
    void should10PaymentByIncorrectYearCard() {
        Card card = DataGenerator.getIncorrectYearCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void should11PaymentByIncorrectHolderCard() {
        Card card = DataGenerator.getIncorrectHolderCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void should12PaymentByIncorrectCvcCard() {
        Card card = DataGenerator.getIncorrectCVCCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }
}
