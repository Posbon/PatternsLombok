import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestCardForm {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void registrationForm() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(DataGenerator.getNewCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id=date] input").setValue(DataGenerator.getFormattedRandomDate());
        $("[data-test-id=name] input").setValue(DataGenerator.getNewName());
        $("[data-test-id=phone] input").setValue(DataGenerator.getNewPhone());
        $("[data-test-id=agreement]").click();
        $(".button__text").click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 5000);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id=date] input").setValue(DataGenerator.getFormattedRandomDate());
        $(".button__text").click();
        $(withText("Необходимо подтверждение")).waitUntil(Condition.visible, 5000);
        $("[data-test-id=replan-notification]").click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 5000);
    }

}