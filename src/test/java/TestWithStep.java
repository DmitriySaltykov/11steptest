import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class TestWithStep {
    private static final String REPOSITORY = "DmitriySaltykov/11steptest";
    private static final int ISSUE = 1;

    @Test
    @DisplayName("Check that issues page contains element with text 1")
    public void stepsTest() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            Selenide.open("https://github.com/");
        });

        step("Search for repository " + REPOSITORY, () -> {
            $(".search-input-container").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });

        step("Click on repository link " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Open Issues tab", () -> {
            $("#issues-tab").click();
        });

        step("Check that issue #" + ISSUE + " is present", () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @DisplayName("Check with steps that issues page contains element with number #1")
    public void testAnnotatedStep() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }
}
