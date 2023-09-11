import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class WebTestSelenide {
    @Test
    @DisplayName("Check that issues page contains element with text Hi!")
    public void testIssueSearch() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        Selenide.open("https://github.com/");
        $(".search-input-container").click();
        $("#query-builder-test").setValue("DmitriySaltykov/11steptest").pressEnter();
        $(By.linkText("DmitriySaltykov/11steptest")).click();
        $("#issues-tab").click();
        $(withText("HI!")).should(Condition.exist);
    }
}
