package dev.blackmon.steps;

import dev.blackmon.loginRunner;
import dev.blackmon.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

//public class LoginFeatureStepImplementation {
public class LoginFeatureStepImpl {

    private final WebDriver driver = loginRunner.driver;
    private final LoginPage loginPage = loginRunner.loginPage;

    @Given("a Employee is on the LoginPage")
    public void a_employee_is_on_the_login_page() {
        driver.get("http://localhost:8080/loginPage.html");
    }

    @When("the Employee types in their {string} and {string} and clicks the LoginButton")
    public void the_employee_types_in_their_username_and_password_and_clicks_the_login_button(String username, String password) {
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginButton.click();
    }

    @Then("the Employee should be on the HomePage")
    public void the_employee_should_be_on_the_home_page() {
        // we need to incorporate a WAIT here....
        // here's an Explicit Wait
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleContains("Home Page"));

        assertEquals("Home Page", driver.getTitle());
    }

}
//}
